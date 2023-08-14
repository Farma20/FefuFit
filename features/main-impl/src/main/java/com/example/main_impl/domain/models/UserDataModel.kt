package com.example.main_impl.domain.models

data class UserDataModel(
    val birthdate: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val phoneNumber: Any,
    val photo: Any,
    val secondName: String,
    val status: String,
    val telegramId: Any,
    val middleName: String,
    val type: String
)

fun UserDataModel.toShort():UserShortDataModel{
    return UserShortDataModel(
        firstName = firstName,
        secondName = secondName,
        middleName = middleName
    )
}