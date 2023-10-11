package com.example.feature_api.refresh_api.dto

data class FeatureRefreshMetaDTO(
    val data: FeatureDataUserMeta,
    val status: String
)

data class FeatureDataUserMeta(
    val qrToken: String,
    val refreshToken: String,
    val token: String,
    val type: String
)