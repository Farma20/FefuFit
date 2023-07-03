package com.example.fefufit.Domain.Repositorys

import com.example.fefufit.Data.Remote.Models.SingInDataModel
import com.example.fefufit.Data.Remote.Models.SingUpDataModel

interface InitializationRepository {
    suspend fun singIn(singInData: SingInDataModel)

    suspend fun singUp(singUpData: SingUpDataModel)
}