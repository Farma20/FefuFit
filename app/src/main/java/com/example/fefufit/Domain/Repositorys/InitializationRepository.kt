package com.example.fefufit.Domain.Repositorys

import com.example.fefufit.Data.Models.InitialModels.SingInDataModel
import com.example.fefufit.Data.Models.InitialModels.SingUpDataModel

interface InitializationRepository {
    suspend fun singIn(singInData: SingInDataModel)

    suspend fun singUp(singUpData: SingUpDataModel)
}