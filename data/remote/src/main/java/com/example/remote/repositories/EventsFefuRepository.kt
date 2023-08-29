package com.example.fefufit.data.remote.repositories

import com.example.fefufit.data.remote.api.FefuFitApi
import com.example.fefufit.data.remote.models.events_data_models.DataUserBookingDataModel
import com.example.remote.EventsDataRepository
import com.example.remote.utils.toMap
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject


class EventsFefuRepository @Inject constructor(
    private val api: FefuFitApi
): EventsDataRepository {
    override suspend fun getAllUserBookings(token:String): DataUserBookingDataModel {
        try {
            return api.getUserBookings(mapOf("token" to token))
        }catch (cause:Throwable){
            val errorText: String = when (cause) {
                is HttpException -> {
                    val result =
                        JSONObject(cause.response()?.errorBody()?.string().toString()).toMap()
                    result["detail"].toString()
                }

                is NullPointerException -> {
                    "User token is null"
                }

                else -> "Unknown error"
            }
            throw Exception(errorText)
        }
    }

}