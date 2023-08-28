package com.example.main_impl.domain.repositories

import com.example.main_impl.domain.models.UserDataModel

interface UserFeatureRepository {

    suspend fun getUserData(token:String): UserDataModel
}