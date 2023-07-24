package com.example.fefufit.Data.Remote.Repositorys

import com.example.fefufit.Data.Remote.API.FefuFitApi
import com.example.fefufit.Data.Remote.Models.InitialDataModels.SingInDataModel
import com.example.fefufit.Data.Remote.Models.InitialDataModels.SingInSuccessResponse
import com.example.fefufit.Data.Remote.Models.InitialDataModels.SingUpDataModel
import com.example.fefufit.Domain.Repositorys.InitializationRepository
import javax.inject.Inject

class InitialFefuRepository @Inject constructor(private val fefuFitApi: FefuFitApi):InitializationRepository {
    override suspend fun singIn(singInData: SingInDataModel): SingInSuccessResponse {
            return fefuFitApi.singIn(singInData)
    }

    override suspend fun singUp(singUpData: SingUpDataModel): Map<String, String> {
        return fefuFitApi.singUp(singUpData)
    }

}