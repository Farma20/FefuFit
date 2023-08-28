package com.example.initialization_impl.domain.repositories

import com.example.initialization_impl.domain.models.FeatureSingInDataModel
import com.example.initialization_impl.domain.models.FeatureSingInSuccessResponse
import com.example.initialization_impl.domain.models.FeatureSingUpDataModel

interface InitializationFeatureRepository {
    suspend fun singIn(singInData: FeatureSingInDataModel): FeatureSingInSuccessResponse

    suspend fun singUp(singUpData: FeatureSingUpDataModel): Map<String, String>
}