package com.example.fefufit.presentation.main_menu.models

import com.example.fefufit.data.remote.models.user_data_models.UserShortDataModel

data class UserDataState(
    val isLoading: Boolean = false,
    val data: UserShortDataModel? = null,
    val isError: String? = null
)
