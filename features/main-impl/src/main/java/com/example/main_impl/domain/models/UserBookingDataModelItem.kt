package com.example.main_impl.domain.models


data class UserBookingDataModelItem(
    val beginTime: String,
    val bookingStatus: String,
    val buildingName: String,
    val coachEmail: String,
    val coachName: String,
    val coachPhoneNumber: String,
    val endTime: String,
    val eventName: String,
    val id: Int,
    val occupiedSpaces: Int,
    val serviceCost: Int,
    val totalSpaces: Int
)