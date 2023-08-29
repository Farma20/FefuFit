package com.example.fefufit.data.remote.repositories


import com.example.fefufit.data.remote.api.FefuFitApi
import com.example.fefufit.data.remote.models.user_data_models.DataUserDataModel
import com.example.remote.UserDataRepository
import com.example.remote.utils.toMap
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class UserFefuRepository @Inject constructor(
    private val api: FefuFitApi,
): UserDataRepository {

    override suspend fun getUserData(token:String): DataUserDataModel {
        try {
            return api.getUserData(mapOf("token" to token))
        }
        catch (cause:Throwable){
            val errorText: String = when (cause) {
                is HttpException -> {
                    val result =
                        JSONObject(cause.response()?.errorBody()?.string().toString()).toMap()
                    result["detail"].toString()
                }

                is NullPointerException -> {
                    "User token is null"
                }

                else -> "Unknown error"
            }
            throw Exception(errorText)
        }
    }

    override suspend fun editUserData(token:String): Map<String, String> {
       return mapOf("don`t_work" to  "don`t_work")
    }
}