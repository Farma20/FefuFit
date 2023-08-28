package com.example.main_impl.domain.repositories

import com.example.main_impl.domain.models.UserServicesDataModel

interface ServicesFeatureRepository {
    suspend fun getUserServices(token:String): UserServicesDataModel
}