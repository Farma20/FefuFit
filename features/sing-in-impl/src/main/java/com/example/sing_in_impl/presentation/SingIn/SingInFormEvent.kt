package com.example.sing_in_impl.presentation.SingIn

sealed class SingInFormEvent{
    data class EmailChanged(val email:String): SingInFormEvent()
    data class PasswordChanged(val password:String): SingInFormEvent()
    object Submit: SingInFormEvent()
}
