package com.example.fefufit.Data.Remote.API

import com.example.fefufit.Data.Remote.Models.SingInDataModel
import com.example.fefufit.Data.Remote.Models.SingUpDataModel
import retrofit2.http.Body
import retrofit2.http.POST

interface FefuFitApi {
    //initialization api
    @POST("/api/auth/login")
    suspend fun singIn(@Body singInData: SingInDataModel)

    @POST("/api/auth/signup")
    suspend fun singUp(@Body singUpData: SingUpDataModel)
}