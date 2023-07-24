package com.example.fefufit.Data.Remote.Models.InitialDataModels


import com.google.gson.annotations.SerializedName

data class InitialUserDataModel(
    @SerializedName("qr_token")
    val qrToken: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("type")
    val type: String
)