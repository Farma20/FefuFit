package com.example.main_impl.domain.repositories

import com.example.main_impl.domain.models.UserBookingDataModel

interface EventsFeatureRepository {
    suspend fun getAllUserBookings(): UserBookingDataModel
}