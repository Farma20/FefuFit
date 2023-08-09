package com.example.sing_in.domain.models

data class InitialUserDataModel(
    val qrToken: String,
    val token: String,
    val type: String
)