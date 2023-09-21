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
    private val metaDataRepository: TimeTableMetaDataRepository
) {
    operator fun invoke(): Flow<Resource<EventDataModel>> = flow{
        try {
            emit(Resource.Loading())
            val userToken = metaDataRepository.getUserTokenMetaData()
            val events = repository.getEvents(userToken)
            emit(Resource.Success(events))
        }
        catch (e: Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}