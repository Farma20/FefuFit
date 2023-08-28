package com.example.remote.repositories

import com.example.fefufit.data.remote.api.FefuFitApi
import com.example.fefufit.data.remote.models.initial_data_models.DataSingInDataModel
import com.example.fefufit.data.remote.models.initial_data_models.DataSingInSuccessResponse
import com.example.fefufit.data.remote.models.initial_data_models.DataSingUpDataModel
import com.example.remote.InitializationDataRepository
import javax.inject.Inject

class InitialFefuRepository @Inject constructor(private val fefuFitApi: FefuFitApi):
    InitializationDataRepository {
    override suspend fun singIn(singInData: DataSingInDataModel): DataSingInSuccessResponse {
            return fefuFitApi.singIn(singInData)
    }

    override suspend fun singUp(singUpData: DataSingUpDataModel): Map<String, String> {
        return fefuFitApi.singUp(singUpData)
    }

}