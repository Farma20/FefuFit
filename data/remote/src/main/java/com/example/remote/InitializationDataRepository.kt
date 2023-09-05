package com.example.remote

import com.example.fefufit.data.remote.models.initial_data_models.DataSingInDataModel
import com.example.fefufit.data.remote.models.initial_data_models.DataSingUpDataModel
import com.example.remote.models.initial_data_models.DataSingInResponse

interface InitializationDataRepository {
    suspend fun singIn(singInData: DataSingInDataModel): DataSingInResponse

    suspend fun singUp(singUpData: DataSingUpDataModel): Map<String, String>
}