package com.example.fefufit.presentation.main_menu.models

import com.example.fefufit.data.remote.models.services_data_models.UserServicesDataModel

data class ActiveServicesState(
    val isLoading:Boolean = false,
    val data: UserServicesDataModel? = null,
    val error: String? = null
)