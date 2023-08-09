package com.example.sing_in.domain.models

data class SingInSuccessResponse(
    val initialUserDataModel: InitialUserDataModel,
    val status: String
)