package com.example.fefufit.glue.timetable_screen.repositories

import com.example.fefufit.glue.timetable_screen.mappers.toEventDataModel
import com.example.remote.EventsDataRepository
import com.example.timetable_impl.domain.models.EventDataModel
import com.example.timetable_impl.domain.repositories.TimeTableEventsFeatureRepository
import javax.inject.Inject

class AdapterEventsTimeTableRepository@Inject constructor(
    private val eventsDataRepository: EventsDataRepository
): TimeTableEventsFeatureRepository  {
    override suspend fun getEvents(token: String): EventDataModel {
        val result = eventsDataRepository.getEvents(token)
        return result.toEventDataModel()
    }
    override suspend fun addEvents(token: String, eventId: Int): Map<String, String> {
        return eventsDataRepository.addEvent(token, eventId)
    }

    override suspend fun cancelEvents(token: String, eventId: Int): Map<String, String> {
        return eventsDataRepository.cancelEvent(token, eventId)
    }
}