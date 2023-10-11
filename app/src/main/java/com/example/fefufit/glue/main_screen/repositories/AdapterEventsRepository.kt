package com.example.fefufit.glue.main_screen.repositories

import com.example.fefufit.glue.main_screen.mappers.toUserBookingDataModel
import com.example.main_impl.domain.models.UserBookingDataModel
import com.example.main_impl.domain.repositories.EventsFeatureRepository
import com.example.remote.EventsDataRepository
import javax.inject.Inject

class AdapterEventsRepository @Inject constructor(
    private val eventsDataRepository: EventsDataRepository
):EventsFeatureRepository {
    override suspend fun getAllUserBookings(token: String): UserBookingDataModel {
        val result = eventsDataRepository.getAllUserBookings(token)
        return result.toUserBookingDataModel()
    }

}