package com.example.fefufit.Data.Remote.Models


import com.google.gson.annotations.SerializedName

data class SingInDataModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)