package com.example.fefufit.data.remote.models.user_data_models


import com.google.gson.annotations.SerializedName

data class DataUserDataModel(
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("phone_number")
    val phoneNumber: Any,
    @SerializedName("photo")
    val photo: Any,
    @SerializedName("second_name")
    val secondName: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("telegram_id")
    val telegramId: Any,
    @SerializedName("third_name")
    val middleName: String,
    @SerializedName("type")
    val type: String
)

fun DataUserDataModel.toShort():DataUserShortDataModel{
    return DataUserShortDataModel(
        firstName = firstName,
        secondName = secondName,
        middleName = middleName
    )
}