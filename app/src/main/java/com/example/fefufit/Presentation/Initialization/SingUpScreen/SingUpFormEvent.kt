package com.example.fefufit.Presentation.Initialization.SingUpScreen

sealed class SingUpFormEvent{
    data class SecondNameChanged(val secondName:String):SingUpFormEvent()
    data class FirstNameChanged(val firstName:String):SingUpFormEvent()
    data class MiddleNameChanged(val middleName:String):SingUpFormEvent()
    data class GenderChanged(val gender: String): SingUpFormEvent()
    data class BirthdayChanged(val birthday:String):SingUpFormEvent()
    data class StatusChanged(val status:String):SingUpFormEvent()
    object Submit:SingUpFormEvent()
}