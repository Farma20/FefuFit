package com.example.fefufit.data.remote.models.initial_data_models


import com.google.gson.annotations.SerializedName

data class InitialUserDataModel(
    @SerializedName("qr_token")
    val qrToken: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("type")
    val type: String
)