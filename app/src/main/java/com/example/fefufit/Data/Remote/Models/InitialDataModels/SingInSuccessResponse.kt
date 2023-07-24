package com.example.fefufit.Data.Remote.Models.InitialDataModels


import com.google.gson.annotations.SerializedName

data class SingInSuccessResponse(
    @SerializedName("data")
    val initialUserDataModel: InitialUserDataModel,
    @SerializedName("status")
    val status: String
)