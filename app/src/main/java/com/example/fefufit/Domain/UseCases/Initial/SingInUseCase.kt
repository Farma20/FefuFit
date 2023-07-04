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
            var errorText: String = ""
            when (cause) {
                is HttpException -> {
                    val result = JSONObject(cause.response()?.errorBody()?.string().toString()).toMap()
                    println(result)

                    if (cause.code() == 400){
                        errorText = when (result["msg"]) {
                            "not an email" -> "Произошла ошибка при регистрации, несуществующая почта"
                            "wrong birthdate" -> "Произошла ошибка при регистрации, некорректная дата рождения"
                            "user already exists" -> "Произошла ошибка при регистрации, пользователь с данной почтой уже существует"
                            else -> "Произошла ошибка при регистрации, проверьте правильность введенных данных"
                        }
                    }

                }
                else-> errorText = "Произошла непредвиденная ошибка. Проверьте соединение с интернетом или свяжитесь с разработчиками"
            }
            emit(Resource.Error(errorText))
        }
    }
}