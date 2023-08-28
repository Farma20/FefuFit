package com.example.fefufit.glue.main_screen.repositories

import com.example.main_impl.domain.models.UserDataModel
import com.example.main_impl.domain.repositories.UserFeatureRepository
import com.example.remote.UserDataRepository
import javax.inject.Inject

class AdapterUserRepository @Inject constructor(
    private val userRepository: UserDataRepository
): UserFeatureRepository {
    override suspend fun getUserData(): UserDataModel {
        val userData = userRepository.getUserData()

        return UserDataModel(
            userData.birthdate,
            userData.email,
            userData.firstName,
            userData.gender,
            userData.phoneNumber,
            userData.photo,
            userData.secondName,
            userData.status,
            userData.telegramId,
            userData.middleName,
            userData.type
        )
    }
}