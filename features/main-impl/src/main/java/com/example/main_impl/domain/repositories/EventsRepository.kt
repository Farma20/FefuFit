package com.example.main_impl.domain.repositories

import com.example.main_impl.domain.models.UserBookingDataModel

interface EventsRepository {
    suspend fun getAllUserBookings(): UserBookingDataModel
}