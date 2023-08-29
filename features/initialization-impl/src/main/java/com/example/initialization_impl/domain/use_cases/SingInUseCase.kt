package com.example.initialization_impl.domain.use_cases

import com.example.common.Resource
import com.example.initialization_impl.domain.models.FeatureSingInDataModel
import com.example.initialization_impl.domain.models.FeatureSingInSuccessResponse
import com.example.initialization_impl.domain.models.FeatureUserMetaData
import com.example.initialization_impl.domain.repositories.SingInFeatureMetaDataRepository
import com.example.initialization_impl.domain.repositories.InitializationFeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SingInUseCase @Inject constructor(
    private val singInRepository: InitializationFeatureRepository,
    private val metaDataRepository: SingInFeatureMetaDataRepository,
) {
    operator fun invoke(singInData: FeatureSingInDataModel): Flow<Resource<FeatureSingInSuccessResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = singInRepository.singIn(singInData)
            metaDataRepository.saveUserMetaData(
                FeatureUserMetaData(
                    userToken = response.initialUserDataModel.token,
                    userQrToken = response.initialUserDataModel.qrToken
                )
            )
            emit(Resource.Success(response))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}