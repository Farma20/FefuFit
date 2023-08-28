package com.example.fefufit.data.remote.repositories

import com.example.fefufit.data.remote.api.FefuFitApi
import com.example.fefufit.data.remote.models.events_data_models.UserBookingDataModel
import com.example.remote.EventsDataRepository
import javax.inject.Inject


class EventsFefuRepository @Inject constructor(
    private val api: FefuFitApi,
//    private val dataStoreManager: DataStoreManager
): EventsDataRepository {
    private var userToken: String? = null

//    init {
//        dataStoreManager.data.onEach {
//            userToken = it.userMetaData.userToken
//        }.launchIn(CoroutineScope(Dispatchers.IO))
//    }

    override suspend fun getAllUserBookings(): UserBookingDataModel {
        return api.getUserBookings(mapOf("token" to userToken!!))
    }

}