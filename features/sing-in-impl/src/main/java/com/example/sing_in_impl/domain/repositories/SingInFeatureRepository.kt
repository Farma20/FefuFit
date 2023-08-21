package com.example.sing_in_impl.domain.repositories

import com.example.sing_in_impl.domain.models.SingInDataModel
import com.example.sing_in_impl.domain.models.SingInSuccessResponse

interface SingInFeatureRepository {
    suspend fun singIn(singInData: SingInDataModel): SingInSuccessResponse
}