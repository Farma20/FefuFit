package com.example.main_impl.domain.repositories

import com.example.main_impl.domain.models.UserServicesDataModel

interface ServicesRepository {
    suspend fun getUserServices(): UserServicesDataModel
}