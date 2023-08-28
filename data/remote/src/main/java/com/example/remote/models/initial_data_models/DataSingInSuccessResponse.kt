package com.example.fefufit.data.remote.models.initial_data_models


import com.google.gson.annotations.SerializedName

data class DataSingInSuccessResponse(
    @SerializedName("data")
    val initialUserDataModel: DataInitialUserDataModel,
    @SerializedName("status")
    val status: String
)