package com.example.fefufit.Domain.Models.ValidationModels

data class SingInFormState (
    val email:String = "",
    val emailError:String? = null,
    val password:String = "",
    val passwordError:String? = null
)