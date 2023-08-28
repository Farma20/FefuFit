package com.example.remote

import com.example.fefufit.data.remote.models.user_data_models.DataUserDataModel


interface UserDataRepository {
    suspend fun getUserData(token:String): DataUserDataModel

    suspend fun editUserData(token:String):Map<String, String>
}