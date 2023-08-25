package com.example.initialization_impl.utils.validation.SingUpValidation

import com.example.initialization_impl.utils.validation.ValidationResult
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