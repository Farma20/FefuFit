package com.example.sing_in_impl.utils.validation.SingInValidation

data class ValidationResult(
    val success: Boolean,
    val errorMessage:String? = null
)
