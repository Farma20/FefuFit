package com.example.sing_in.domain.use_cases

import com.example.common.Resource
import com.example.fefufit.data.internal.data_store.DataStoreManager
import com.example.fefufit.data.internal.data_store.entities.UserMetaData
import com.example.fefufit.data.remote.models.initial_data_models.SingInDataModel
import com.example.fefufit.data.remote.models.initial_data_models.SingInSuccessResponse
import com.example.fefufit.domain.repositories.InitializationRepository
import com.example.fefufit.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject


class SingInUseCase @Inject constructor(
    private val repository: InitializationRepository,
    private val dataStoreManager:DataStoreManager,
) {
    operator fun invoke(singInData: SingInDataModel): Flow<Resource<SingInSuccessResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.singIn(singInData)
            dataStoreManager.setUserMetaData(
                UserMetaData(
                    userToken = response.initialUserDataModel.token,
                    userQrToken = response.initialUserDataModel.qrToken
                )
            )
            emit(Resource.Success(response))
        }catch (cause:Throwable){
            when (cause) {
                is HttpException -> {
                    if (cause.code() == 400){
                        val errorText =
                            "Ошибка входа. Проверьте корректность введенных данных или зарегистрируйтесь"
                        emit(Resource.Error(errorText))
                    }

                }
                else -> {
                    val errorText =
                        "Ошибка входа. Проверьте соединение с интернетом или свяжитесь с разработчиками"
                    emit(Resource.Error(errorText))
                }
            }
        }
    }
}