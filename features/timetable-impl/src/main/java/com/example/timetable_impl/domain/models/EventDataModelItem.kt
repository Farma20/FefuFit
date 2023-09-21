package com.example.timetable_impl.domain.models

import java.time.LocalDate

data class EventDataModelItem(
    val areaName: String,
    val day:LocalDate,
    val beginTime: String,
    val buildingName: String,
    val coachEmail: String,
    val coachName: String,
    val coachPhoneNumber: Any?,
    val endTime: String,
    val eventName: String,
    val id: Int,
    val occupiedSpaces: Int,
    val status: String,
    val totalSpaces: Int
)