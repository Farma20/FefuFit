package com.example.sing_up_impl.domain.models


data class SingUpDataModel(
    val birthdate: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val password: String,
    val phoneNumber: Any,
    val secondName: String,
    val status: String,
    val thirdName: String
)