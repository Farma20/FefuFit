package com.example.fefufit.data.remote.repositories

import com.example.fefufit.data.remote.api.FefuFitApi
import com.example.fefufit.data.remote.models.initial_data_models.SingInDataModel
import com.example.fefufit.data.remote.models.initial_data_models.SingInSuccessResponse
import com.example.fefufit.data.remote.models.initial_data_models.SingUpDataModel
import com.example.fefufit.domain.repositories.InitializationRepository
import javax.inject.Inject

class InitialFefuRepository @Inject constructor(private val fefuFitApi: FefuFitApi):InitializationRepository {
    override suspend fun singIn(singInData: SingInDataModel): SingInSuccessResponse {
            return fefuFitApi.singIn(singInData)
    }

    override suspend fun singUp(singUpData: SingUpDataModel): Map<String, String> {
        return fefuFitApi.singUp(singUpData)
    }

}