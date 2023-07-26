package com.example.fefufit.Domain.Repositorys

import com.example.fefufit.Data.Remote.Models.UserDataModels.UserDataModel

interface UserDataRepository {

    suspend fun getUserData():UserDataModel

    suspend fun editUserData():Map<String, String>
}