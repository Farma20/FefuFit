package com.example.fefufit.glue.initialization.repositories

import com.example.remote.InitializationRepository
import com.example.initialization_impl.domain.models.FeatureInitialUserDataModel
import com.example.initialization_impl.domain.models.FeatureSingInDataModel
import com.example.initialization_impl.domain.models.FeatureSingInSuccessResponse
import com.example.initialization_impl.domain.repositories.InitializationFeatureRepository
import javax.inject.Inject

class AdapterSingInRepository @Inject constructor(
    private val initialRepository: InitializationRepository
):InitializationFeatureRepository {
    override suspend fun singIn(singInData: FeatureSingInDataModel): FeatureSingInSuccessResponse {
        val data = com.example.fefufit.data.remote.models.initial_data_models.SingInDataModel(
            singInData.email,
            singInData.password
        )
        val result = initialRepository.singIn(data)

        return FeatureSingInSuccessResponse(
            initialUserDataModel = FeatureInitialUserDataModel(
                result.initialUserDataModel.qrToken,
                result.initialUserDataModel.token,
                result.initialUserDataModel.type,
            ),
            status = result.status
        )
    }
}