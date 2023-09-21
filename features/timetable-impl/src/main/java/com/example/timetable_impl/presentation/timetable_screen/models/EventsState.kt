package com.example.timetable_impl.presentation.timetable_screen.models

import com.example.timetable_impl.domain.models.EventDataModel

data class EventsState(
    val isLoading:Boolean = false,
    val data: EventDataModel? = null,
    val error: String? = null
)