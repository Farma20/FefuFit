package com.example.sing_up_impl.utils.validation

import javax.inject.Inject

class ValidateStatusUseCase @Inject constructor() {
    operator fun invoke(status: String): ValidationResult {
        if (status.isBlank()) {
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