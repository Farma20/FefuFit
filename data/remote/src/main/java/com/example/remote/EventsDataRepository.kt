package com.example.remote

import com.example.fefufit.data.remote.models.events_data_models.DataUserBookingDataModel
import com.example.remote.models.events_data_models.DataEventDataModel

interface EventsDataRepository {
    suspend fun getAllUserBookings(token:String): DataUserBookingDataModel

    suspend fun getEvents(token: String): DataEventDataModel

    suspend fun addEvent(token: String, eventId:Int) :Map<String,String>

    suspend fun cancelEvent(token: String, eventId:Int) :Map<String,String>
}