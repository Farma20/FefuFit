package com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation

import com.example.fefufit.domain.models.validation_models.ValidationResult
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