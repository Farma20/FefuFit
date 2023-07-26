package com.example.fefufit.domain.models.validation_models

data class ValidationResult(
    val success: Boolean,
    val errorMessage:String? = null
)
