package com.example.fefufit.Data.Models.InitialModels


import com.google.gson.annotations.SerializedName

data class SingInDataModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)