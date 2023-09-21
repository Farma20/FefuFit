package com.example.timetable_impl.domain.repositories

import com.example.timetable_impl.domain.models.EventDataModel

interface TimeTableEventsFeatureRepository {
    suspend fun getEvents(token:String):EventDataModel
}