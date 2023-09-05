package com.example.fefufit.glue.initialization.repositories

import com.example.fefufit.glue.initialization.mappers.toDataSingInDataModel
import com.example.fefufit.glue.initialization.mappers.toDataSingUpDataModel
import com.example.fefufit.glue.initialization.mappers.toFeatureSingInSuccessResponse
import com.example.remote.InitializationDataRepository
import com.example.initialization_impl.domain.models.FeatureSingInDataModel
import com.example.initialization_impl.domain.models.FeatureSingInResponse
import com.example.initialization_impl.domain.models.FeatureSingUpDataModel
import com.example.initialization_impl.domain.repositories.InitializationFeatureRepository
import javax.inject.Inject

class AdapterInitializationRepository @Inject constructor(
    private val initialRepository: InitializationDataRepository
):InitializationFeatureRepository {
    override suspend fun singIn(singInData: FeatureSingInDataModel): FeatureSingInResponse {
        val result = initialRepository.singIn(singInData.toDataSingInDataModel())

        return result.toFeatureSingInSuccessResponse()
    }

    override suspend fun singUp(singUpData: FeatureSingUpDataModel): Map<String, String> {
        return initialRepository.singUp(singUpData.toDataSingUpDataModel())
    }
}