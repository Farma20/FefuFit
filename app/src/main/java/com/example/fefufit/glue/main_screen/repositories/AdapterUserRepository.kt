package com.example.fefufit.glue.main_screen.repositories

import com.example.fefufit.glue.main_screen.mappers.toUserDataModel
import com.example.main_impl.domain.models.UserDataModel
import com.example.main_impl.domain.repositories.UserFeatureRepository
import com.example.remote.UserDataRepository
import javax.inject.Inject

class AdapterUserRepository @Inject constructor(
    private val userRepository: UserDataRepository
): UserFeatureRepository {
    override suspend fun getUserData(token: String): UserDataModel {
        val userData = userRepository.getUserData(token)

        return userData.toUserDataModel()
    }
}