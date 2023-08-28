package com.example.main_impl.domain.use_cases

import com.example.common.Resource
import com.example.main_impl.domain.models.UserShortDataModel
import com.example.main_impl.domain.models.toShort
import com.example.main_impl.domain.repositories.MainMetaDataRepository
import com.example.main_impl.domain.repositories.UserFeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserShortDataUseCase @Inject constructor(
    private val repository: UserFeatureRepository,
    private val metaDataRepository: MainMetaDataRepository
) {
    operator fun invoke(): Flow<Resource<UserShortDataModel>> = flow {
        try {
            emit(Resource.Loading())
            val userToken = metaDataRepository.getUserTokenMetaData()
            val userData = repository.getUserData(userToken)
            emit(Resource.Success(userData.toShort()))
        }
        catch (cause:Throwable){
            emit(Resource.Error("error in UserShortDataUseCase"))
        }
    }
}

//TODO DELETE

//when (cause) {
//    is HttpException -> {
//        val result = JSONObject(cause.response()?.errorBody()?.string().toString()).toMap()
//        emit(Resource.Error(result["detail"].toString()))
//    }
//    is NullPointerException -> {
//        val errorText = "User token is null"
//        emit(Resource.Error(errorText))
//    }
//    else -> emit(Resource.Error("Unknown error"))
//}