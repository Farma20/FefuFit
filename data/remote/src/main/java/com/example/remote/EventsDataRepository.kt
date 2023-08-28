package com.example.remote

import com.example.fefufit.data.remote.models.events_data_models.UserBookingDataModel

interface EventsDataRepository {
    suspend fun getAllUserBookings(token:String): UserBookingDataModel
}