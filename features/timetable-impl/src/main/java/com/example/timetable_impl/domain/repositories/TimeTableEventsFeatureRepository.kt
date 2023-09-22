package com.example.timetable_impl.domain.repositories

import com.example.timetable_impl.domain.models.EventDataModel

interface TimeTableEventsFeatureRepository {
    suspend fun getEvents(token:String):EventDataModel

    suspend fun addEvents(token: String, eventId:Int): Map<String, String>

    suspend fun cancelEvents(token: String, eventId:Int): Map<String, String>
}