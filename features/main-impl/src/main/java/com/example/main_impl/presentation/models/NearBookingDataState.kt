package com.example.main_impl.presentation.models

import com.example.main_impl.domain.models.UserBookingDataModelItem

data class NearBookingDataState(
    val isLoading: Boolean = false,
    val data: UserBookingDataModelItem? = null,
    val error:String? = null
)
