package com.example.sing_in_impl.utils.validation.SingInValidation

data class SingInFormState (
    val email:String = "",
    val emailError:String? = null,
    val password:String = "",
    val passwordError:String? = null
)