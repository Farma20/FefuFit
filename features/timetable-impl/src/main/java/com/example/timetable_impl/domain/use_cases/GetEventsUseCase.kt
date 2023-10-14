package com.example.timetable_impl.domain.use_cases

import com.example.common.Resource
import com.example.timetable_impl.domain.models.EventDataModel
import com.example.timetable_impl.domain.repositories.TimeTableEventsFeatureRepository
import com.example.timetable_impl.domain.repositories.TimeTableMetaDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val repository: TimeTableEventsFeatureRepository,
    private val metaDataRepository: TimeTableMetaDataRepository,
    private val refreshUseCase: RefreshUseCase,
) {
    operator fun invoke(): Flow<Resource<EventDataModel>> = flow{
        try {
            emit(Resource.Loading())
            val events = getData()
            emit(Resource.Success(events))
        }
        catch (e: Exception){
            if (e.message.toString() == "token is invalid"){
                refreshUseCase()
                val events = getData()
                emit(Resource.Success(events))
            }
            else{
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private suspend fun getData(): EventDataModel {
        val userToken = metaDataRepository.getUserTokenMetaData()
        return repository.getEvents(userToken)
    }
}