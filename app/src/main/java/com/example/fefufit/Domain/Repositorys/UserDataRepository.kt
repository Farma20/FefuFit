package com.example.fefufit.Domain.Repositorys

import com.example.fefufit.Data.Remote.Models.UserDataModels.UserInfoDataModel

interface UserDataRepository {

    suspend fun getUserData():UserInfoDataModel

    suspend fun editUserData():Map<String, String>
}