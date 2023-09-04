package com.example.main_impl.domain.models


data class UserBookingDataModelItem(
    val id: Int,
    val eventName: String,
    val buildingName: String,
    val coachEmail: String,
    val coachName: String,
    val coachPhoneNumber: String,
    val beginData:String,
    val beginTime: String,
    val endTime: String,
    val bookingStatus: String?,
    val serviceCost: Int,
    val occupiedSpaces: Int,
    val totalSpaces: Int
)