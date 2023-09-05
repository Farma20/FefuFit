package com.example.initialization_impl.domain.models

data class FeatureSingInResponse(
    val data: UserMeta,
    val status: String
)
data class UserMeta(
    val qrToken: String,
    val refreshToken: String,
    val token: String,
    val type: String
)