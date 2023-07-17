package com.example.fefufit.Domain.Models.ValidationModels

data class SingUpSecondFormState(
    val phoneNumber:String = "",
    val phoneNumberError:String? = null,
    val email:String = "",
    val emailError:String? = null,
    val password:String = "",
    val passwordError:String? = null,
    val repeatPassword:String = "",
    val repeatPasswordError:String? = null,
    val terms:Boolean = false,
    val termsError:String? = null,
)
