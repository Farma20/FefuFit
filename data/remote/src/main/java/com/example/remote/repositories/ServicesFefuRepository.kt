package com.example.remote.repositories

import com.example.fefufit.data.remote.models.services_data_models.DataUserServicesDataModel
import com.example.remote.ServicesDataRepository
import com.example.remote.data_source.FefuFitApi
import com.example.remote.utils.WrapperRetrofitException
import javax.inject.Inject

class ServicesFefuRepository @Inject constructor(
    private val api: FefuFitApi,
    private val exceptionClass: WrapperRetrofitException
): ServicesDataRepository {
    override suspend fun getUserServices(token:String): DataUserServicesDataModel =
        exceptionClass.wrapRetrofitException {
            api.getActiveUserPlans(mapOf("token" to token))
        }
}