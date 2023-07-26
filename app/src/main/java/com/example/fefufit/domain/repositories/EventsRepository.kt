package com.example.fefufit.domain.repositories

import com.example.fefufit.data.remote.models.events_data_models.UserBookingDataModel

interface EventsRepository {
    suspend fun getAllUserBookings():UserBookingDataModel
}