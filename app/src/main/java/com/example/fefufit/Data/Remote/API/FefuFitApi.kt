package com.example.fefufit.Data.Remote.API

import com.example.fefufit.Data.Remote.Models.InitialModels.SingInDataModel
import com.example.fefufit.Data.Remote.Models.InitialModels.SingInSuccessResponse
import com.example.fefufit.Data.Remote.Models.InitialModels.SingUpDataModel
import retrofit2.http.Body
import retrofit2.http.POST

interface FefuFitApi {
    //initialization api
    @POST("/api/auth/login")
    suspend fun singIn(@Body singInData: SingInDataModel):SingInSuccessResponse

    @POST("/api/auth/signup")
    suspend fun singUp(@Body singUpData: SingUpDataModel):Map<String, String>
}