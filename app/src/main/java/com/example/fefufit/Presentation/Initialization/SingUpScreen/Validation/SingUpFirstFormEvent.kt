package com.example.fefufit.Presentation.Initialization.SingUpScreen.Validation

sealed class SingUpFirstFormEvent{
    data class SecondNameChanged(val secondName:String): SingUpFirstFormEvent()
    data class FirstNameChanged(val firstName:String): SingUpFirstFormEvent()
    data class MiddleNameChanged(val middleName:String): SingUpFirstFormEvent()
    data class GenderChanged(val gender: String): SingUpFirstFormEvent()
    data class BirthdayChanged(val birthday:String): SingUpFirstFormEvent()
    data class StatusChanged(val status:String): SingUpFirstFormEvent()
    object Submit: SingUpFirstFormEvent()
}