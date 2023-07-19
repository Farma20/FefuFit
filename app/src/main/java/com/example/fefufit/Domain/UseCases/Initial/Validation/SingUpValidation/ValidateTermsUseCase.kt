package com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation

import android.util.Patterns
import com.example.fefufit.Domain.Models.ValidationModels.ValidationResult
import javax.inject.Inject

class ValidateTermsUseCase @Inject constructor() {
    operator fun invoke(terms: Boolean): ValidationResult {
        if (!terms) {
            return ValidationResult(
                success = false,
                errorMessage = "Пожалуйста, примите условия"
            )
        }
        return ValidationResult(
            success = true
        )
    }
}