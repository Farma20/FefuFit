package com.example.fefufit.Domain.UseCases.Main.UsersUseCases

import com.example.fefufit.Data.Remote.Models.UserDataModels.UserShortDataModel
import com.example.fefufit.Data.Remote.Models.UserDataModels.toShort
import com.example.fefufit.Domain.Repositorys.UserDataRepository
import com.example.fefufit.Utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
        catch (e:Exception){
            val errorText = "Ошибка, данные не были получены"
            emit(Resource.Error(errorText))
        }
    }
}