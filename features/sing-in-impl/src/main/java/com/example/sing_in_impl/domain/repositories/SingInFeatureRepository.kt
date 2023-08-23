package com.example.sing_in_impl.domain.repositories

import com.example.sing_in_impl.domain.models.FeatureSingInDataModel
import com.example.sing_in_impl.domain.models.FeatureSingInSuccessResponse

interface SingInFeatureRepository {
    suspend fun singIn(singInData: FeatureSingInDataModel): FeatureSingInSuccessResponse
}