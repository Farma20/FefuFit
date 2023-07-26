package com.example.fefufit.Domain.UseCases.Main.UsersUseCases

import com.example.fefufit.Data.Remote.Models.UserDataModels.UserShortDataModel
import com.example.fefufit.Domain.Repositorys.UserDataRepository
import com.example.fefufit.Utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserShortDataUseCase(
    private val repository: UserDataRepository
) {
    operator fun invoke(): Flow<Resource<UserShortDataModel>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getUserData()

        }
    }
}