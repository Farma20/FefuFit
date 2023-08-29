package com.example.remote.repositories

import com.example.fefufit.data.remote.api.FefuFitApi
import com.example.fefufit.data.remote.models.initial_data_models.DataSingInDataModel
import com.example.fefufit.data.remote.models.initial_data_models.DataSingInSuccessResponse
import com.example.fefufit.data.remote.models.initial_data_models.DataSingUpDataModel
import com.example.remote.InitializationDataRepository
import retrofit2.HttpException
import javax.inject.Inject

private const val INPUT_ERROR_TEXT =
    "Ошибка входа. Проверьте корректность введенных данных или зарегистрируйтесь"

private const val INTERNET_ERROR_TEXT =
    "Ошибка входа. Проверьте соединение с интернетом или свяжитесь с разработчиками"

class InitialFefuRepository @Inject constructor(private val fefuFitApi: FefuFitApi) :
    InitializationDataRepository {
    override suspend fun singIn(singInData: DataSingInDataModel): DataSingInSuccessResponse {
        try {
            return fefuFitApi.singIn(singInData)
        } catch (cause: Throwable) {

            var errorText: String = ""

            when (cause) {
                is HttpException ->
                    if (cause.code() == 400)
                        errorText = INPUT_ERROR_TEXT

                else -> errorText = INTERNET_ERROR_TEXT
            }
            throw Exception(errorText)
        }
    }

    override suspend fun singUp(singUpData: DataSingUpDataModel): Map<String, String> {
        return fefuFitApi.singUp(singUpData)
    }

}