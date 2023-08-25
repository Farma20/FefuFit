package com.example.initialization_impl.utils.validation.SingUpValidation

import com.example.initialization_impl.utils.validation.ValidationResult
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