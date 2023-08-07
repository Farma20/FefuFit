package com.example.fefufit.presentation.main_menu.models

import com.example.fefufit.data.remote.models.events_data_models.UserBookingDataModelItem

data class NearBookingDataState(
    val isLoading: Boolean = false,
    val data: UserBookingDataModelItem? = null,
    val error:String? = null
)
