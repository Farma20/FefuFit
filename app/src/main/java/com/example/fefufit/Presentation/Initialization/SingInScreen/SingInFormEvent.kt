package com.example.fefufit.Presentation.Initialization.SingInScreen

sealed class SingInFormEvent{
    data class EmailChanged(val email:String):SingInFormEvent()
    data class PasswordChanged(val password:String):SingInFormEvent()
    object Submit:SingInFormEvent()
}
