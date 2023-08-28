package com.example.remote

import com.example.fefufit.data.remote.models.services_data_models.DataUserServicesDataModel

interface ServicesDataRepository {
    suspend fun getUserServices(token:String): DataUserServicesDataModel
}