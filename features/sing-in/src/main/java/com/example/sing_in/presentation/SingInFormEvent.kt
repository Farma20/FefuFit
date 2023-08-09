package com.example.sing_in.presentation

sealed class SingInFormEvent{
    data class EmailChanged(val email:String):SingInFormEvent()
    data class PasswordChanged(val password:String):SingInFormEvent()
    object Submit:SingInFormEvent()
}
