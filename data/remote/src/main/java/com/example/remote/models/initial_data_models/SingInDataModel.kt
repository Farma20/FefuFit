package com.example.fefufit.data.remote.models.initial_data_models


import com.google.gson.annotations.SerializedName

data class SingInDataModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)