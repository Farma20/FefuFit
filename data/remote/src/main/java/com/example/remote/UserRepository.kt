package com.example.remote

import com.example.fefufit.data.remote.models.user_data_models.UserDataModel

interface UserRepository {
    suspend fun getUserData(): UserDataModel

    suspend fun editUserData():Map<String, String>
}