package com.example.fefufit.domain.use_cases.main.user_use_cases

import com.example.fefufit.data.remote.models.services_data_models.UserServicesDataModel
import com.example.fefufit.domain.repositories.ServicesRepository
import com.example.fefufit.utils.Resource
import com.example.fefufit.utils.toMap
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