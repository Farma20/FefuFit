package com.example.fefufit.glue.initialization.repositories

import com.example.remote.InitializationDataRepository
import com.example.initialization_impl.domain.models.FeatureInitialUserDataModel
import com.example.initialization_impl.domain.models.FeatureSingInDataModel
import com.example.initialization_impl.domain.models.FeatureSingInSuccessResponse
import com.example.initialization_impl.domain.models.SingUpDataModel
import com.example.initialization_impl.domain.repositories.InitializationFeatureRepository
import javax.inject.Inject

class InitializationRepository @Inject constructor(
    private val initialRepository: InitializationDataRepository
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

    override suspend fun singUp(singUpData: SingUpDataModel): Map<String, String> {
        return initialRepository.singUp(
            com.example.fefufit.data.remote.models.initial_data_models.SingUpDataModel(
                singUpData.birthdate,
                singUpData.email,
                singUpData.firstName,
                singUpData.gender,
                singUpData.password,
                singUpData.phoneNumber,
                singUpData.secondName,
                singUpData.status,
                singUpData.thirdName
            )
        )
    }
}