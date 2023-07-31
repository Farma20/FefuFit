package com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation

import com.example.fefufit.domain.models.validation_models.ValidationResult
import javax.inject.Inject

class ValidateSecondNameUseCase @Inject constructor() {
    operator fun invoke(secondName: String): ValidationResult {
        if (secondName.isBlank()) {
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