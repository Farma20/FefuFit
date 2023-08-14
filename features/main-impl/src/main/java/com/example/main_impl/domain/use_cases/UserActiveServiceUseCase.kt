package com.example.main_impl.domain.use_cases

import com.example.common.Resource
import com.example.main_impl.domain.models.UserServicesDataModel
import com.example.main_impl.domain.repositories.ServicesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
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
            emit(Resource.Error("error in UserActiveServicesUseCase"))
        }
    }
}

//TODO DELETE

//            when (cause) {
//                is HttpException -> {
//                    val result = JSONObject(cause.response()?.errorBody()?.string().toString()).toMap()
//                    emit(Resource.Error(result["detail"].toString()))
//                }
//                is NullPointerException -> {
//                    val errorText = "User token is null"
//                    emit(Resource.Error(errorText))
//                }
//                else -> emit(Resource.Error("Unknown error"))
//            }