package com.example.remote.repositories

import com.example.remote.data_source.FefuFitApi
import com.example.fefufit.data.remote.models.initial_data_models.DataSingInDataModel
import com.example.remote.InitializationDataRepository
import com.example.remote.models.initial_data_models.DataSingInResponse
import com.example.remote.models.initial_data_models.DataSingUpDataModel
import com.example.remote.models.initial_data_models.DataSingUpResponse
import com.example.remote.utils.toMap
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

private const val INPUT_SING_IN_ERROR_TEXT =
    "Ошибка входа. Проверьте корректность введенных данных или зарегистрируйтесь"

private const val INTERNET_ERROR_TEXT =
    "Произошла непредвиденная ошибка. Проверьте соединение с интернетом или свяжитесь с разработчиками"

private const val SING_UP_EMAIL_ERROR = "Произошла ошибка при регистрации, несуществующая почта"
private const val SERVER_EMAIL_ERROR_RESULT = "not an email"

private const val SING_UP_BIRTHDAY_ERROR = "Произошла ошибка при регистрации, некорректная дата рождения"
private const val SERVER_BIRTHDAY_ERROR_RESULT = "wrong birthdate"

private const val SING_UP_USER_EXIST_ERROR = "Произошла ошибка при регистрации, пользователь с данной почтой уже существует"
private const val SERVER_USER_EXIST_ERROR_RESULT = "user already exist"

private const val SING_UP_DATA_ERROR = "Произошла ошибка при регистрации, проверьте правильность введенных данных"


class InitialFefuRepository @Inject constructor(private val fefuFitApi: FefuFitApi) :
    InitializationDataRepository {
    override suspend fun singIn(singInData: DataSingInDataModel): DataSingInResponse {
        try {
            return fefuFitApi.singIn(singInData)
        } catch (cause: Throwable) {
            val errorText: String = when (cause) {
                is HttpException -> {
                    if (cause.code() == 400)
                        INPUT_SING_IN_ERROR_TEXT
                    else
                        INTERNET_ERROR_TEXT
                }
                else -> {
                    INTERNET_ERROR_TEXT
                }
            }
            throw Exception(errorText)
        }
    }

    override suspend fun singUp(singUpData: DataSingUpDataModel): DataSingUpResponse {
        try {
            return fefuFitApi.singUp(singUpData)
        } catch (cause: Throwable) {
            val errorText = when (cause) {
                is HttpException -> {
                    val result =
                        JSONObject(cause.response()?.errorBody()?.string().toString()).toMap()

                    if (cause.code() == 400) {
                        when (result["detail"]) {
                            SERVER_EMAIL_ERROR_RESULT -> SING_UP_EMAIL_ERROR
                            SERVER_BIRTHDAY_ERROR_RESULT-> SING_UP_BIRTHDAY_ERROR
                            SERVER_USER_EXIST_ERROR_RESULT-> SING_UP_USER_EXIST_ERROR
                            else -> SING_UP_DATA_ERROR
                        }
                    } else {
                        INTERNET_ERROR_TEXT
                    }
                }

                else -> {
                    INTERNET_ERROR_TEXT
                }
            }
            throw Exception(errorText)
        }
    }

}