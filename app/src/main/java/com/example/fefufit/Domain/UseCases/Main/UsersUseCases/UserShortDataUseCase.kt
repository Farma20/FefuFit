package com.example.fefufit.Domain.UseCases.Main.UsersUseCases

import com.example.fefufit.Data.Remote.Models.UserDataModels.UserShortDataModel
import com.example.fefufit.Data.Remote.Models.UserDataModels.toShort
import com.example.fefufit.Domain.Repositorys.UserDataRepository
import com.example.fefufit.Utils.Resource
import com.example.fefufit.Utils.toMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class UserShortDataUseCase @Inject constructor(
    private val repository: UserDataRepository
) {
    operator fun invoke(): Flow<Resource<UserShortDataModel>> = flow {
        try {
            emit(Resource.Loading())
            val userData = repository.getUserData()
            emit(Resource.Success(userData.toShort()))
        }
        catch (cause:Throwable){
            when (cause) {
                is HttpException -> {
                    val result = JSONObject(cause.response()?.errorBody()?.string().toString()).toMap()
                    emit(Resource.Error(result["detail"].toString()))
                }
                is NullPointerException -> {
                    val errorText = "User token is null"
                    emit(Resource.Error(errorText))
                }
                else -> emit(Resource.Error("Unknown error"))
        }
    }
    }
}