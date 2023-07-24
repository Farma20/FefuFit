package com.example.fefufit.Domain.UseCases.Initial

import com.example.fefufit.Data.Internal.DataStore.DataStoreManager
import com.example.fefufit.Data.Internal.DataStore.Entities.UserMetaData
import com.example.fefufit.Data.Remote.Models.InitialDataModels.SingInDataModel
import com.example.fefufit.Data.Remote.Models.InitialDataModels.SingInSuccessResponse
import com.example.fefufit.Domain.Repositorys.InitializationRepository
import com.example.fefufit.Utils.Resource
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