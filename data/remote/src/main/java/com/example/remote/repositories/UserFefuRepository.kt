package com.example.fefufit.data.remote.repositories


import com.example.fefufit.data.remote.api.FefuFitApi
import com.example.fefufit.data.remote.models.user_data_models.UserDataModel
import com.example.remote.UserDataRepository
import javax.inject.Inject

class UserFefuRepository @Inject constructor(
    private val api: FefuFitApi,
): UserDataRepository {

    override suspend fun getUserData(token:String): UserDataModel {
        return api.getUserData(mapOf("token" to token))
    }

    override suspend fun editUserData(token:String): Map<String, String> {
       return mapOf("don`t_work" to  "don`t_work")
    }
}