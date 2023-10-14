package com.example.main_impl.domain.use_cases

import com.example.common.Resource
import com.example.main_impl.domain.models.UserDataModel
import com.example.main_impl.domain.models.UserShortDataModel
import com.example.main_impl.domain.models.toShort
import com.example.main_impl.domain.repositories.MainMetaDataRepository
import com.example.main_impl.domain.repositories.UserFeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserShortDataUseCase @Inject constructor(
    private val repository: UserFeatureRepository,
    private val metaDataRepository: MainMetaDataRepository,
    private val refreshUseCase: RefreshUseCase
) {
    operator fun invoke(): Flow<Resource<UserShortDataModel>> = flow {
        try {
            emit(Resource.Loading())
            val userData = getData()
            emit(Resource.Success(userData.toShort()))
        }
        catch (e:Exception){
            if (e.message.toString() == "token is invalid"){
                refreshUseCase()
                val userData = getData()
                emit(Resource.Success(userData.toShort()))
            }
            else{
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private suspend fun getData(): UserDataModel {
        val userToken = metaDataRepository.getUserTokenMetaData()
        return repository.getUserData(userToken)
    }
}
