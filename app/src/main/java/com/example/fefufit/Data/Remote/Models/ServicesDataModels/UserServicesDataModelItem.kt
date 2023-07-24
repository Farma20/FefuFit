package com.example.fefufit.Data.Remote.Models.ServicesDataModels


import com.google.gson.annotations.SerializedName

data class UserServicesDataModelItem(
    @SerializedName("events_done")
    val eventsDone: Int,
    @SerializedName("exp_date")
    val expDate: String,
    @SerializedName("plan_capacity")
    val planCapacity: Int,
    @SerializedName("plan_id")
    val planId: Int,
    @SerializedName("service_name")
    val serviceName: String
)