package com.example.fefufit.domain.repositories

import com.example.fefufit.data.remote.models.initial_data_models.SingInDataModel
import com.example.fefufit.data.remote.models.initial_data_models.SingInSuccessResponse
import com.example.fefufit.data.remote.models.initial_data_models.SingUpDataModel

interface InitializationRepository {
    suspend fun singIn(singInData: SingInDataModel):SingInSuccessResponse

    suspend fun singUp(singUpData: SingUpDataModel): Map<String, String>
}