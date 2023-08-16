package com.example.sing_up_impl.utils.validation

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