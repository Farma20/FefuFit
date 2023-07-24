package com.example.fefufit.Data.Remote.Repositorys

import com.example.fefufit.Data.Internal.DataStore.DataStoreManager
import com.example.fefufit.Data.Remote.API.FefuFitApi
import com.example.fefufit.Data.Remote.Models.ServicesDataModels.UserServicesDataModel
import com.example.fefufit.Domain.Repositorys.ServicesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ServicesFefuRepository @Inject constructor(
    private val api: FefuFitApi,
    private val dataStoreManager: DataStoreManager
):ServicesRepository{

    private var userToken:String? = null

    init {
        dataStoreManager.data.onEach {
            userToken = it.userMetaData.userToken
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override suspend fun getUserServices(): UserServicesDataModel {
        return api.getActiveUserPlans(mapOf("token" to userToken!!))
    }

}