package com.example.sing_in_impl.domain.use_cases

import com.example.common.Resource
import com.example.sing_in_impl.domain.models.SingInDataModel
import com.example.sing_in_impl.domain.models.SingInSuccessResponse
import com.example.sing_in_impl.domain.repositories.SingInFeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SingInUseCase @Inject constructor(
    private val repository: SingInFeatureRepository,
//    private val dataStoreManager:DataStoreManager,
) {
    operator fun invoke(singInData: SingInDataModel): Flow<Resource<SingInSuccessResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.singIn(singInData)
//            dataStoreManager.setUserMetaData(
//                UserMetaData(
//                    userToken = response.initialUserDataModel.token,
//                    userQrToken = response.initialUserDataModel.qrToken
//                )
//            )
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