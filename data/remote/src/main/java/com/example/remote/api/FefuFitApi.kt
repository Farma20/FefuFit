package com.example.fefufit.data.remote.api

import com.example.fefufit.data.remote.models.events_data_models.UserBookingDataModel
import com.example.fefufit.data.remote.models.initial_data_models.SingInDataModel
import com.example.fefufit.data.remote.models.initial_data_models.SingInSuccessResponse
import com.example.fefufit.data.remote.models.initial_data_models.SingUpDataModel
import com.example.fefufit.data.remote.models.services_data_models.UserServicesDataModel
import com.example.fefufit.data.remote.models.user_data_models.UserDataModel
import retrofit2.http.Body
import retrofit2.http.POST

interface FefuFitApi {
    //initialization api
    @POST("/api/auth/login")
    suspend fun singIn(@Body singInData: SingInDataModel): SingInSuccessResponse

    @POST("/api/auth/signup")
    suspend fun singUp(@Body singUpData: SingUpDataModel):Map<String, String>

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