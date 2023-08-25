package com.example.sing_in_impl.domain.repositories

import com.example.sing_in_impl.domain.models.FeatureSingInDataModel
import com.example.sing_in_impl.domain.models.FeatureSingInSuccessResponse
import com.example.sing_in_impl.domain.models.SingUpDataModel

interface InitializationFeatureRepository {
    suspend fun singIn(singInData: FeatureSingInDataModel): FeatureSingInSuccessResponse

    suspend fun singUp(singUpData: SingUpDataModel): Map<String, String>
}