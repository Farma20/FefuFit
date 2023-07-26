package com.example.fefufit.domain.repositories

import com.example.fefufit.data.remote.models.services_data_models.UserServicesDataModel

interface ServicesRepository {
    suspend fun getUserServices():UserServicesDataModel
}