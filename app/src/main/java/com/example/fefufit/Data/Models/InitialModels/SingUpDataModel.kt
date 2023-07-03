package com.example.fefufit.Data.Models.InitialModels


import com.google.gson.annotations.SerializedName

data class SingUpDataModel(
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: Any,
    @SerializedName("second_name")
    val secondName: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("third_name")
    val thirdName: String
)