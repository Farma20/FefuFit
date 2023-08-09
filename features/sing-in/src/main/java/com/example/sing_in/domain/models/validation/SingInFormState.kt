package com.example.sing_in.domain.models.validation

data class SingInFormState (
    val email:String = "",
    val emailError:String? = null,
    val password:String = "",
    val passwordError:String? = null
)