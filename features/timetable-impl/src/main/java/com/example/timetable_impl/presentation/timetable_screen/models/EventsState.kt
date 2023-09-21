package com.example.main_impl.presentation.models

import com.example.main_impl.domain.models.UserServicesDataModel


data class ActiveServicesState(
    val isLoading:Boolean = false,
    val data: UserServicesDataModel? = null,
    val error: String? = null
)