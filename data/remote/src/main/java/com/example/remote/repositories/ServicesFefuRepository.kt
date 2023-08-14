package com.example.fefufit.data.remote.repositories

import com.example.fefufit.data.remote.api.FefuFitApi
import com.example.fefufit.data.remote.models.services_data_models.UserServicesDataModel
import com.example.remote.ServicesRepository
import javax.inject.Inject

class ServicesFefuRepository @Inject constructor(
    private val api: FefuFitApi,
//    private val dataStoreManager: DataStoreManager
): ServicesRepository {

    private var userToken:String? = null

//    init {
//        dataStoreManager.data.onEach {
//            userToken = it.userMetaData.userToken
//        }.launchIn(CoroutineScope(Dispatchers.IO))
//    }

    override suspend fun getUserServices(): UserServicesDataModel {
        return api.getActiveUserPlans(mapOf("token" to userToken!!))
    }

}