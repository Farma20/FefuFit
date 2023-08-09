package com.example.sing_in.domain.models.validation

data class ValidationResult(
    val success: Boolean,
    val errorMessage:String? = null
)
