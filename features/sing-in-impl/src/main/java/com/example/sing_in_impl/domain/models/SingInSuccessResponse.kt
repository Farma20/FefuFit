package com.example.sing_in_impl.domain.models


data class SingInSuccessResponse(
    val initialUserDataModel: InitialUserDataModel,
    val status: String
)