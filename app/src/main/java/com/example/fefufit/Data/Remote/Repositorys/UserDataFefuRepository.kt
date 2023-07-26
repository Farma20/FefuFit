package com.example.fefufit.Data.Remote.Repositorys

import com.example.fefufit.Data.Internal.DataStore.DataStoreManager
import com.example.fefufit.Data.Remote.API.FefuFitApi
import com.example.fefufit.Data.Remote.Models.UserDataModels.UserDataModel
import com.example.fefufit.Domain.Repositorys.UserDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class UserDataFefuRepository @Inject constructor(
   private val api: FefuFitApi,
   private val dataStoreManager: DataStoreManager
): UserDataRepository {

    private var userToken:String? = null

    init {
        dataStoreManager.data.onEach {
            userToken = it.userMetaData.userToken
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override suspend fun getUserData(): UserDataModel {
        return api.getUserData(mapOf("token" to userToken!!))
    }

    override suspend fun editUserData(): Map<String, String> {
       return mapOf("don`t_work" to  "don`t_work")
    }
}