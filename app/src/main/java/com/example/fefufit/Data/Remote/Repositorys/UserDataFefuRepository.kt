package com.example.fefufit.Data.Remote.Repositorys

import com.example.fefufit.Data.Remote.Models.UserDataModels.UserInfoDataModel
import com.example.fefufit.Domain.Repositorys.UserDataRepository

class UserDataFefuRepository(): UserDataRepository {
    override suspend fun getUserData(): UserInfoDataModel {
        TODO("Not yet implemented")
    }

    override suspend fun editUserData(): Map<String, String> {
        TODO("Not yet implemented")
    }
}