package com.example.fefufit.Domain.Repositorys

import com.example.fefufit.Data.Remote.Models.InitialModels.SingInDataModel
import com.example.fefufit.Data.Remote.Models.InitialModels.SingInSuccessResponse
import com.example.fefufit.Data.Remote.Models.InitialModels.SingUpDataModel

interface InitializationRepository {
    suspend fun singIn(singInData: SingInDataModel):SingInSuccessResponse

    suspend fun singUp(singUpData: SingUpDataModel): Map<String, String>
}