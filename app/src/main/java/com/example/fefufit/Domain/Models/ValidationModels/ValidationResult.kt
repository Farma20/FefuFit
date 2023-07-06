package com.example.fefufit.Domain.Models.ValidationModels

data class ValidationResult(
    val success: Boolean,
    val errorMessage:String? = null
)
