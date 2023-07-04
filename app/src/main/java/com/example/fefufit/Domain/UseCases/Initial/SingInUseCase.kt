package com.example.fefufit.Domain.UseCases.Initial

import com.example.fefufit.Data.Remote.Models.InitialModels.SingInDataModel
import com.example.fefufit.Data.Remote.Models.InitialModels.SingInSuccessResponse
import com.example.fefufit.Domain.Repositorys.InitializationRepository
import com.example.fefufit.Utils.Resource
import com.example.fefufit.Utils.toMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class SingInUseCase @Inject constructor(
    private val repository: InitializationRepository
) {
    operator fun invoke(singInData: SingInDataModel): Flow<Resource<SingInSuccessResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.singIn(singInData)
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