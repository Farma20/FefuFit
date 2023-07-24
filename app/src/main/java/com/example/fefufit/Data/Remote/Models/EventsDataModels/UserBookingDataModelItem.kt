package com.example.fefufit.Data.Remote.Models.EventsDataModels


import com.google.gson.annotations.SerializedName

data class UserBookingDataModelItem(
    @SerializedName("begin_time")
    val beginTime: String,
    @SerializedName("booking_status")
    val bookingStatus: String,
    @SerializedName("building_name")
    val buildingName: String,
    @SerializedName("coach_email")
    val coachEmail: String,
    @SerializedName("coach_name")
    val coachName: String,
    @SerializedName("coach_phone_number")
    val coachPhoneNumber: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("event_name")
    val eventName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("occupied_spaces")
    val occupiedSpaces: Int,
    @SerializedName("service_cost")
    val serviceCost: Int,
    @SerializedName("total_spaces")
    val totalSpaces: Int
)