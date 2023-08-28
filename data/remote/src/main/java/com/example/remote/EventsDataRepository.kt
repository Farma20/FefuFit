package com.example.remote

import com.example.fefufit.data.remote.models.events_data_models.DataUserBookingDataModel

interface EventsDataRepository {
    suspend fun getAllUserBookings(token:String): DataUserBookingDataModel
}