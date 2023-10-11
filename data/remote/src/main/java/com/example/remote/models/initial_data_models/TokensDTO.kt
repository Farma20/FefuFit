package com.example.remote.models.initial_data_models

import com.google.gson.annotations.SerializedName

data class TokenDTO(
    @SerializedName("token")
    val token: String
)

data class RefreshTokenDTO(
    @SerializedName("refresh_token")
    val refreshToken: String
)
