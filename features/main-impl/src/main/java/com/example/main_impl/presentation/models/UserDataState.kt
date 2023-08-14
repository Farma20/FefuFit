package com.example.main_impl.presentation.models

import com.example.main_impl.domain.models.UserShortDataModel

data class UserDataState(
    val isLoading: Boolean = false,
    val data: UserShortDataModel? = null,
    val error: String? = null
)
