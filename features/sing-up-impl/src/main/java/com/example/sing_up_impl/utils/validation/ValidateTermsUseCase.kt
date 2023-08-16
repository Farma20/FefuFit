package com.example.sing_up_impl.utils.validation

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