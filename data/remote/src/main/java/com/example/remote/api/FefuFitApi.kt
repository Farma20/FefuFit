package com.example.fefufit.data.remote.api

import com.example.fefufit.data.remote.models.events_data_models.DataUserBookingDataModel
import com.example.fefufit.data.remote.models.initial_data_models.DataSingInDataModel
import com.example.fefufit.data.remote.models.services_data_models.DataUserServicesDataModel
import com.example.fefufit.data.remote.models.user_data_models.DataUserDataModel
import com.example.remote.models.events_data_models.DataEventDataModel
import com.example.remote.models.initial_data_models.DataSingInResponse
import com.example.remote.models.initial_data_models.DataSingUpDataModel
import com.example.remote.models.initial_data_models.DataSingUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface FefuFitApi {
    //initialization api
    @POST("/api2/auth/login")
    suspend fun singIn(@Body singInData: DataSingInDataModel): DataSingInResponse

    @POST("/api2/auth/signup")
    suspend fun singUp(@Body singUpData: DataSingUpDataModel):DataSingUpResponse

    //userData api
    @POST("/api/user/user/view_self")
    suspend fun getUserData(@Body token: Map<String, String>): DataUserDataModel


    //events api
    @POST("/api/timetable/booking/user/view_all")
    suspend fun getUserBookings(@Body token: Map<String, String>):DataUserBookingDataModel

    @GET("/api2/event/view")
    suspend fun getEvents(@Header("auth") token: String): DataEventDataModel

    @POST("/api2/booking/add/event{event_id}")
    suspend fun addEvent(@Header("auth") token: String, @Path("event_id") eventsId: Int): Map<String, String>

    @POST("/api2/booking/cancel/event{event_id}")
    suspend fun cancelEvent(@Header("auth") token: String, @Path("event_id") eventsId: Int): Map<String, String>

    //service api
    @POST("/api/plan/view_next")
    suspend fun getActiveUserPlans(@Body token: Map<String, String>): DataUserServicesDataModel
}