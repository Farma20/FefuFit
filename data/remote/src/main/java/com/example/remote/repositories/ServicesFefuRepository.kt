package com.example.fefufit.data.remote.repositories

import com.example.fefufit.data.remote.api.FefuFitApi
import com.example.fefufit.data.remote.models.services_data_models.DataUserServicesDataModel
import com.example.remote.ServicesDataRepository
import javax.inject.Inject

class ServicesFefuRepository @Inject constructor(
    private val api: FefuFitApi
): ServicesDataRepository {
    override suspend fun getUserServices(token:String): DataUserServicesDataModel {
        return api.getActiveUserPlans(mapOf("token" to token))
    }

}