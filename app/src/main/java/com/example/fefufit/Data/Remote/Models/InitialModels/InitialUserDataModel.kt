package com.example.fefufit.Data.Remote.Models.InitialModels


import com.google.gson.annotations.SerializedName

data class InitialUserDataModel(
    @SerializedName("qr_token")
    val qrToken: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("type")
    val type: String
)