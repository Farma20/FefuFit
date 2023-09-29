package com.example.remote.utils

import com.example.common.BackendException
import com.example.common.ConnectionException
import com.example.common.ParseBackendResponseException
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException


class WrapperRetrofitException(){
    suspend fun <T> wrapRetrofitException(block:suspend () -> T):T {
        return try {
            block()
        } catch (e:JsonParseException){
            throw ParseBackendResponseException(e)
        } catch (e:JSONException){
            throw  ParseBackendResponseException(e)
        } catch (e: HttpException){
            throw createBackendException(e)
        } catch (e: IOException){
            throw ConnectionException(INTERNET_ERROR_TEXT)
        }
    }

    private fun createBackendException(e: HttpException): Exception {
        return try {
            val errorBody = Gson().fromJson(
                e.response()!!.errorBody()!!.string(),
                ErrorResponseBody::class.java
            )
            BackendException(e.code(), locateHttpExceptionsResult(errorBody.detail))

        } catch (e:Exception){
            ParseBackendResponseException(e)
        }
    }

    private fun locateHttpExceptionsResult(errorBody:String):String{
        return when(errorBody){
            SERVER_EMAIL_ERROR_RESULT -> SING_UP_EMAIL_ERROR
            SERVER_BIRTHDAY_ERROR_RESULT -> SING_UP_BIRTHDAY_ERROR
            SERVER_USER_EXIST_ERROR_RESULT -> SING_UP_USER_EXIST_ERROR
            SERVER_INITIAL_ERROR_RESULT -> INPUT_SING_IN_ERROR_TEXT
            SERVER_INITIAL_PASSWORD_ERROR_RESULT -> INPUT_SING_IN_ERROR_TEXT
            else -> errorBody
        }
    }

    data class ErrorResponseBody(
        @SerializedName("detail")
        val detail:String
    )
}
