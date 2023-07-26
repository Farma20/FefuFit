package com.example.fefufit.domain.repositories

import com.example.fefufit.data.remote.models.user_data_models.UserDataModel

interface UserDataRepository {

    suspend fun getUserData():UserDataModel

    suspend fun editUserData():Map<String, String>
}