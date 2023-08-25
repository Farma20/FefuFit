package com.example.sing_in_impl.utils.validation.SingUpValidation

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