package com.example.fefufit.Data.Remote.API

import com.example.fefufit.Data.Remote.Models.EventsData.UserBookingDataModel
import com.example.fefufit.Data.Remote.Models.InitialModels.SingInDataModel
import com.example.fefufit.Data.Remote.Models.InitialModels.SingInSuccessResponse
import com.example.fefufit.Data.Remote.Models.InitialModels.SingUpDataModel
import com.example.fefufit.Data.Remote.Models.UserDataModels.UserInfoDataModel
import retrofit2.http.Body
import retrofit2.http.POST

interface FefuFitApi {
    //initialization api
    @POST("/api/auth/login")
    suspend fun singIn(@Body singInData: SingInDataModel):SingInSuccessResponse

    @POST("/api/auth/signup")
    suspend fun singUp(@Body singUpData: SingUpDataModel):Map<String, String>


    //userData api
    @POST("/api/user/user/view_self")
    suspend fun getUserData(@Body token: Map<String, String>):UserInfoDataModel


    //events api
    @POST("/api/timetable/booking/user/view_all")
    suspend fun getUserBookings(@Body token: Map<String, String>):UserBookingDataModel
}