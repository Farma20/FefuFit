package com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation

import com.example.fefufit.domain.models.validation_models.ValidationResult
import javax.inject.Inject

class ValidateFirstNameUseCase @Inject constructor() {
    operator fun invoke(firstName: String): ValidationResult {
        if (firstName.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Поле не должно быть пустым"
            )
        }
        return ValidationResult(
            success = true
        )
    }
}