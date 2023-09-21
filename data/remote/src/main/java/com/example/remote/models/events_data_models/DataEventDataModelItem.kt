package com.example.remote.models.events_data_models


import com.google.gson.annotations.SerializedName

data class DataEventDataModelItem(
    @SerializedName("area_name")
    val areaName: String,
    @SerializedName("begin_time")
    val beginTime: String,
    @SerializedName("building_name")
    val buildingName: String,
    @SerializedName("coach_email")
    val coachEmail: String,
    @SerializedName("coach_name")
    val coachName: String,
    @SerializedName("coach_phone_number")
    val coachPhoneNumber: Any?,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("event_name")
    val eventName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("occupied_spaces")
    val occupiedSpaces: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("total_spaces")
    val totalSpaces: Int
)