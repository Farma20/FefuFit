package com.example.fefufit.data.remote.api

import com.example.fefufit.data.remote.models.events_data_models.UserBookingDataModel
import com.example.fefufit.data.remote.models.services_data_models.UserServicesDataModel
import com.example.fefufit.data.remote.models.user_data_models.UserDataModel
import retrofit2.http.Body
import retrofit2.http.POST

interface FefuFitApi {
    //userData api
    @POST("/api/user/user/view_self")
    suspend fun getUserData(@Body token: Map<String, String>):UserDataModel


    //events api
    @POST("/api/timetable/booking/user/view_all")
    suspend fun getUserBookings(@Body token: Map<String, String>):UserBookingDataModel


    //service api
    @POST("/api/plan/view_next")
    suspend fun getActiveUserPlans(@Body token: Map<String, String>): UserServicesDataModel
}