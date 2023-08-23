package com.example.sing_in_impl.domain.models


data class FeatureInitialUserDataModel(
    val qrToken: String,
    val token: String,
    val type: String
)