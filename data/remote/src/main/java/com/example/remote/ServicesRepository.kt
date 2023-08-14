package com.example.remote

import com.example.fefufit.data.remote.models.services_data_models.UserServicesDataModel

interface ServicesRepository {
    suspend fun getUserServices(): UserServicesDataModel
}