package com.example.remote.repositories

import com.example.remote.data_source.FefuFitApi
import com.example.fefufit.data.remote.models.events_data_models.DataUserBookingDataModel
import com.example.remote.EventsDataRepository
import com.example.remote.models.events_data_models.DataEventDataModel
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

    override suspend fun getEvents(token: String): DataEventDataModel {
        try {
            return api.getEvents(token)
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

    override suspend fun addEvent(token: String, eventId: Int): Map<String, String> {
        try {
            return api.addEvent(token, eventId)
        }
        catch (cause: Throwable){
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

    override suspend fun cancelEvent(token: String, eventId: Int): Map<String, String> {
        try {
            return api.cancelEvent(token, eventId)
        }catch (cause: Throwable){
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