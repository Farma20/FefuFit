package com.example.remote.repositories


import com.example.fefufit.data.remote.models.user_data_models.DataUserDataModel
import com.example.remote.UserDataRepository
import com.example.remote.data_source.FefuFitApi
import com.example.remote.utils.WrapperRetrofitException
import javax.inject.Inject

class UserFefuRepository @Inject constructor(
    private val api: FefuFitApi,
    private val exceptionClass: WrapperRetrofitException
): UserDataRepository {

    override suspend fun getUserData(token:String): DataUserDataModel =
        exceptionClass.wrapRetrofitException {
            api.getUserData(mapOf("token" to token))
        }

    override suspend fun editUserData(token:String): Map<String, String> =
        exceptionClass.wrapRetrofitException {
            mapOf("don`t_work" to  "don`t_work")
        }
}