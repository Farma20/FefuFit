package com.example.initialization_impl.domain.use_cases

import com.example.common.Resource
import com.example.initialization_impl.domain.models.FeatureSingUpDataModel
import com.example.initialization_impl.domain.models.FeatureSingUpResponse
import com.example.initialization_impl.domain.repositories.InitializationFeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SingUpUseCase @Inject constructor(private val repository: InitializationFeatureRepository) {
    operator fun invoke(singUpData: FeatureSingUpDataModel): Flow<Resource<FeatureSingUpResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.singUp(singUpData)
            emit(Resource.Success(response))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}