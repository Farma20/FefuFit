package com.example.remote

import com.example.fefufit.data.remote.models.initial_data_models.DataSingInDataModel
import com.example.remote.models.initial_data_models.DataSingInResponse
import com.example.remote.models.initial_data_models.DataSingUpDataModel
import com.example.remote.models.initial_data_models.DataSingUpResponse

interface InitializationDataRepository {
    suspend fun singIn(singInData: DataSingInDataModel): DataSingInResponse

    suspend fun singUp(singUpData: DataSingUpDataModel): DataSingUpResponse
}