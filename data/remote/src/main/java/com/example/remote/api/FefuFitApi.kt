package com.example.fefufit.data.remote.api

import com.example.fefufit.data.remote.models.events_data_models.DataUserBookingDataModel
import com.example.fefufit.data.remote.models.initial_data_models.DataSingInDataModel
import com.example.fefufit.data.remote.models.initial_data_models.DataSingInSuccessResponse
import com.example.fefufit.data.remote.models.initial_data_models.DataSingUpDataModel
import com.example.fefufit.data.remote.models.services_data_models.DataUserServicesDataModel
import com.example.fefufit.data.remote.models.user_data_models.DataUserDataModel
import retrofit2.http.Body
import retrofit2.http.POST

interface FefuFitApi {
    //initialization api
    @POST("/api/auth/login")
    suspend fun singIn(@Body singInData: DataSingInDataModel): DataSingInSuccessResponse

    @POST("/api/auth/signup")
    suspend fun singUp(@Body singUpData: DataSingUpDataModel):Map<String, String>

    //userData api
    @POST("/api/user/user/view_self")
    suspend fun getUserData(@Body token: Map<String, String>): DataUserDataModel


    //events api
    @POST("/api/timetable/booking/user/view_all")
    suspend fun getUserBookings(@Body token: Map<String, String>):DataUserBookingDataModel


    //service api
    @POST("/api/plan/view_next")
    suspend fun getActiveUserPlans(@Body token: Map<String, String>): DataUserServicesDataModel
}