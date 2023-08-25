package com.example.initialization_impl.domain.repositories

import com.example.initialization_impl.domain.models.FeatureSingInDataModel
import com.example.initialization_impl.domain.models.FeatureSingInSuccessResponse
import com.example.initialization_impl.domain.models.SingUpDataModel

interface InitializationFeatureRepository {
    suspend fun singIn(singInData: FeatureSingInDataModel): FeatureSingInSuccessResponse

    suspend fun singUp(singUpData: SingUpDataModel): Map<String, String>
}