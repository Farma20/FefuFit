package com.example.fefufit.Domain.UseCases.Main.UsersUseCases

import com.example.fefufit.Data.Remote.Models.ServicesDataModels.UserServicesDataModel
import com.example.fefufit.Domain.Repositorys.EventsRepository
import com.example.fefufit.Domain.Repositorys.ServicesRepository
import com.example.fefufit.Utils.Resource
import com.example.fefufit.Utils.toMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class UserActiveServiceUseCase @Inject constructor(
    private val repository: ServicesRepository
) {
    operator fun invoke(): Flow<Resource<UserServicesDataModel?>> = flow{
        try {
            emit(Resource.Loading())
            val userServices = repository.getUserServices()
            if (userServices.isNotEmpty())
                emit(Resource.Success(userServices))
            else
                emit(Resource.Success(null))
        }
        catch (cause: Throwable){
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