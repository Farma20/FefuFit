package com.example.fefufit.Presentation.Initialization.SingUpScreen.Validation

sealed class SingUpSecondFormEvent{
    data class PhoneNumberChanged(val phoneNumber:String): SingUpSecondFormEvent()
    data class EmailChanged(val email:String): SingUpSecondFormEvent()
    data class PasswordChanged(val password:String): SingUpSecondFormEvent()
    data class RepeatPasswordChanged(val repeatPassword: String): SingUpSecondFormEvent()
    data class TermsChanged(val terms:Boolean): SingUpSecondFormEvent()
    object Submit: SingUpSecondFormEvent()
}