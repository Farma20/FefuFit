package com.example.remote.repositories

import com.example.remote.data_source.FefuFitApi
import com.example.fefufit.data.remote.models.services_data_models.DataUserServicesDataModel
import com.example.remote.ServicesDataRepository
import com.example.remote.utils.toMap
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class ServicesFefuRepository @Inject constructor(
    private val api: FefuFitApi
): ServicesDataRepository {
    override suspend fun getUserServices(token:String): DataUserServicesDataModel {
        try {
            return api.getActiveUserPlans(mapOf("token" to token))
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
}