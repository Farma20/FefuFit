package com.example.fefufit.glue.sing_in.repositories

import com.example.remote.InitializationRepository
import com.example.sing_in_impl.domain.models.InitialUserDataModel
import com.example.sing_in_impl.domain.models.SingInDataModel
import com.example.sing_in_impl.domain.models.SingInSuccessResponse
import com.example.sing_in_impl.domain.repositories.SingInFeatureRepository
import javax.inject.Inject

class AdapterSingInRepository @Inject constructor(
    private val initialRepository: InitializationRepository
):SingInFeatureRepository {
    override suspend fun singIn(singInData: SingInDataModel): SingInSuccessResponse {
        val data = com.example.fefufit.data.remote.models.initial_data_models.SingInDataModel(
            singInData.email,
            singInData.password
        )
        val result = initialRepository.singIn(data)

        return SingInSuccessResponse(
            initialUserDataModel = InitialUserDataModel(
                result.initialUserDataModel.qrToken,
                result.initialUserDataModel.token,
                result.initialUserDataModel.type,
            ),
            status = result.status
        )
    }
}