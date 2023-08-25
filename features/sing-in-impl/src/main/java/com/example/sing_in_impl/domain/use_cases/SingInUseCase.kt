package com.example.sing_in_impl.domain.use_cases

import com.example.common.Resource
import com.example.sing_in_impl.domain.models.FeatureSingInDataModel
import com.example.sing_in_impl.domain.models.FeatureSingInSuccessResponse
import com.example.sing_in_impl.domain.models.FeatureUserMetaData
import com.example.sing_in_impl.domain.repositories.SingInFeatureMetaDataRepository
import com.example.sing_in_impl.domain.repositories.InitializationFeatureRepository
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
        }catch (e:Error){
            emit(Resource.Error("$e"))
//            when (cause) {
//                is HttpException -> {
//                    if (cause.code() == 400){
//                        val errorText =
//                            "Ошибка входа. Проверьте корректность введенных данных или зарегистрируйтесь"
//                        emit(Resource.Error(errorText))
//                    }
//
//                }
//                else -> {
//                    val errorText =
//                        "Ошибка входа. Проверьте соединение с интернетом или свяжитесь с разработчиками"
//                    emit(Resource.Error(errorText))
//                }
//            }
        }
    }
}