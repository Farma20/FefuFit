package com.example.fefufit.data.remote.models.initial_data_models


import com.google.gson.annotations.SerializedName

data class SingInSuccessResponse(
    @SerializedName("data")
    val initialUserDataModel: InitialUserDataModel,
    @SerializedName("status")
    val status: String
)