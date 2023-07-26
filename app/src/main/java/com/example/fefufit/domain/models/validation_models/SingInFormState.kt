package com.example.fefufit.domain.models.validation_models

data class SingInFormState (
    val email:String = "",
    val emailError:String? = null,
    val password:String = "",
    val passwordError:String? = null
)