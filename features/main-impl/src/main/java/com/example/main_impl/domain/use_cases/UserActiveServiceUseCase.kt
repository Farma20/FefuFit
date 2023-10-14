package com.example.main_impl.domain.use_cases

import com.example.common.Resource
import com.example.feature_api.refresh_api.dto.FeatureRefreshMetaDTO
import com.example.main_impl.domain.models.UserServicesDataModel
import com.example.main_impl.domain.repositories.MainMetaDataRepository
import com.example.main_impl.domain.repositories.ServicesFeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserActiveServiceUseCase @Inject constructor(
    private val repository: ServicesFeatureRepository,
    private val metaDataRepository: MainMetaDataRepository,
    private val refreshUseCase: RefreshUseCase
) {
    operator fun invoke(): Flow<Resource<UserServicesDataModel?>> = flow{
        try {
            emit(Resource.Loading())
            val userServices = getData()
            if (userServices.isNotEmpty())
                emit(Resource.Success(userServices))
            else
                emit(Resource.Success(null))
        }
        catch (e: Exception){
            if (e.message.toString() == "invalid token"){
                refreshUseCase()
                val userServices = getData()
                if (userServices.isNotEmpty())
                    emit(Resource.Success(userServices))
                else
                    emit(Resource.Success(null))
            }
            else{
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private suspend fun getData(): UserServicesDataModel{
        val userToken = metaDataRepository.getUserTokenMetaData()
        return repository.getUserServices(userToken)
    }
}