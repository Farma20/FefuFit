package com.example.fefufit.Domain.Repositorys

import com.example.fefufit.Data.Remote.Models.InitialDataModels.SingInDataModel
import com.example.fefufit.Data.Remote.Models.InitialDataModels.SingInSuccessResponse
import com.example.fefufit.Data.Remote.Models.InitialDataModels.SingUpDataModel

interface InitializationRepository {
    suspend fun singIn(singInData: SingInDataModel):SingInSuccessResponse

    suspend fun singUp(singUpData: SingUpDataModel): Map<String, String>
}