package com.example.fefufit.Data.Remote.Models.InitialModels


import com.google.gson.annotations.SerializedName

data class SingInSuccessResponse(
    @SerializedName("data")
    val initialUserDataModel: InitialUserDataModel,
    @SerializedName("status")
    val status: String
)