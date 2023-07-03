package com.example.fefufit.Data.Remote.API

import com.example.fefufit.Data.Remote.Models.SingInDataModel
import com.example.fefufit.Data.Remote.Models.SingUpDataModel

interface FefuFitApi {
    //initialization api
    suspend fun singIn(singInData: SingInDataModel)

    suspend fun singUp(singUpData: SingUpDataModel)
}