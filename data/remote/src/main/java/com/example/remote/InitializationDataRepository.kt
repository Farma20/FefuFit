package com.example.remote

import com.example.fefufit.data.remote.models.initial_data_models.SingInDataModel
import com.example.fefufit.data.remote.models.initial_data_models.SingInSuccessResponse
import com.example.fefufit.data.remote.models.initial_data_models.SingUpDataModel

interface InitializationDataRepository {
    suspend fun singIn(singInData: SingInDataModel):SingInSuccessResponse

    suspend fun singUp(singUpData: SingUpDataModel): Map<String, String>
}