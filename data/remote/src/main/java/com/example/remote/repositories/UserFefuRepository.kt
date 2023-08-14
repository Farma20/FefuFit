package com.example.fefufit.data.remote.repositories


import com.example.fefufit.data.remote.api.FefuFitApi
import com.example.fefufit.data.remote.models.user_data_models.UserDataModel
import com.example.remote.UserRepository
import javax.inject.Inject

class UserFefuRepository @Inject constructor(
    private val api: FefuFitApi,
//   private val dataStoreManager: DataStoreManager
): UserRepository {

    private var userToken:String? = null

//    init {
//        dataStoreManager.data.onEach {
//            userToken = it.userMetaData.userToken
//        }.launchIn(CoroutineScope(Dispatchers.IO))
//    }

    override suspend fun getUserData(): UserDataModel {
        return api.getUserData(mapOf("token" to userToken!!))
    }

    override suspend fun editUserData(): Map<String, String> {
       return mapOf("don`t_work" to  "don`t_work")
    }
}