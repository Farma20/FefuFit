package com.example.sing_in_impl.utils.validation

data class ValidationResult(
    val success: Boolean,
    val errorMessage:String? = null
)
