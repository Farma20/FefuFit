package com.example.fefufit.Data.Remote.Models.UserDataModels


import com.google.gson.annotations.SerializedName

data class UserInfoDataModel(
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
    val thirdName: String,
    @SerializedName("type")
    val type: String
)