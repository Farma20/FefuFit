package com.example.remote

import com.example.fefufit.data.remote.models.user_data_models.UserDataModel

interface UserDataRepository {
    suspend fun getUserData(token:String): UserDataModel

    suspend fun editUserData(token:String):Map<String, String>
}