package com.example.main_impl.domain.repositories

import com.example.main_impl.domain.models.UserDataModel

interface UserDataRepository {

    suspend fun getUserData(): UserDataModel
}