package com.example.remote.repositories

import com.example.fefufit.data.remote.models.events_data_models.DataUserBookingDataModel
import com.example.remote.EventsDataRepository
import com.example.remote.data_source.FefuFitApi
import com.example.remote.models.events_data_models.DataEventDataModel
import com.example.remote.utils.WrapperRetrofitException
import javax.inject.Inject


class EventsFefuRepository @Inject constructor(
    private val api: FefuFitApi,
    private val exceptionClass: WrapperRetrofitException
): EventsDataRepository {
    override suspend fun getAllUserBookings(token:String): DataUserBookingDataModel =
        exceptionClass.wrapRetrofitException {
            api.getUserBookings(mapOf("token" to token))
        }

    override suspend fun getEvents(token: String): DataEventDataModel =
        exceptionClass.wrapRetrofitException {
            api.getEvents(token)
        }


    override suspend fun addEvent(token: String, eventId: Int): Map<String, String> =
        exceptionClass.wrapRetrofitException {
            api.addEvent(token, eventId)
        }

    override suspend fun cancelEvent(token: String, eventId: Int): Map<String, String> =
        exceptionClass.wrapRetrofitException {
            api.cancelEvent(token, eventId)
        }

}