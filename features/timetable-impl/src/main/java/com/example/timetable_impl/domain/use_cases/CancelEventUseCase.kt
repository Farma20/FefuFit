package com.example.timetable_impl.domain.use_cases

import com.example.common.Resource
import com.example.timetable_impl.domain.models.EventActionResponse
import com.example.timetable_impl.domain.repositories.TimeTableEventsFeatureRepository
import com.example.timetable_impl.domain.repositories.TimeTableMetaDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CancelEventUseCase @Inject constructor(
    private val eventsRepository: TimeTableEventsFeatureRepository,
    private val metaDataRepository: TimeTableMetaDataRepository,
    private val refreshUseCase: RefreshUseCase
) {
    operator fun invoke(eventId: Int): Flow<Resource<EventActionResponse>> = flow {
        try {
            emit(Resource.Loading())
            val eventResponse = getData(eventId)
            emit(Resource.Success(eventResponse))
        }
        catch (e:Exception){

            if (e.message.toString() == "token is invalid"){
                refreshUseCase()
                val eventResponse = getData(eventId)
                emit(Resource.Success(eventResponse))
            }
            else{
                val eventResponse = EventActionResponse(e.message.toString())
                emit(Resource.Error("error", eventResponse))
            }
        }
    }

    private suspend fun getData(eventId: Int): EventActionResponse {
        val token = metaDataRepository.getUserTokenMetaData()
        val result = eventsRepository.cancelEvents(token, eventId)
        return EventActionResponse(result["detail"]!!)
    }
}