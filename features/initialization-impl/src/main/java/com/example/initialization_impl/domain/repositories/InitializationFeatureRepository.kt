package com.example.initialization_impl.domain.repositories

import com.example.initialization_impl.domain.models.FeatureSingInDataModel
import com.example.initialization_impl.domain.models.FeatureSingInResponse
import com.example.initialization_impl.domain.models.FeatureSingUpDataModel
import com.example.initialization_impl.domain.models.FeatureSingUpResponse

interface InitializationFeatureRepository {
    suspend fun singIn(singInData: FeatureSingInDataModel): FeatureSingInResponse

    suspend fun singUp(singUpData: FeatureSingUpDataModel): FeatureSingUpResponse
}