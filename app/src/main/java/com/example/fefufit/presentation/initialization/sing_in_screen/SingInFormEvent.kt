package com.example.fefufit.presentation.initialization.sing_in_screen

sealed class SingInFormEvent{
    data class EmailChanged(val email:String):SingInFormEvent()
    data class PasswordChanged(val password:String):SingInFormEvent()
    object Submit:SingInFormEvent()
}
