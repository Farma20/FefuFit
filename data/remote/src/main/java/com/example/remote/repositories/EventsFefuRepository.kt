package com.example.fefufit.data.remote.repositories

import com.example.fefufit.data.remote.api.FefuFitApi
import com.example.fefufit.data.remote.models.events_data_models.DataUserBookingDataModel
import com.example.remote.EventsDataRepository
import javax.inject.Inject


class EventsFefuRepository @Inject constructor(
    private val api: FefuFitApi
): EventsDataRepository {
    override suspend fun getAllUserBookings(token:String): DataUserBookingDataModel {
        return api.getUserBookings(mapOf("token" to token))
    }

}