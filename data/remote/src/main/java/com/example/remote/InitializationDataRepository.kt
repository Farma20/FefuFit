package com.example.remote

import com.example.fefufit.data.remote.models.initial_data_models.DataSingInDataModel
import com.example.fefufit.data.remote.models.initial_data_models.DataSingInSuccessResponse
import com.example.fefufit.data.remote.models.initial_data_models.DataSingUpDataModel

interface InitializationDataRepository {
    suspend fun singIn(singInData: DataSingInDataModel):DataSingInSuccessResponse

    suspend fun singUp(singUpData: DataSingUpDataModel): Map<String, String>
}