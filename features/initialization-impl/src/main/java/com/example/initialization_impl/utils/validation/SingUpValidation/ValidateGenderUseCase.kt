package com.example.initialization_impl.utils.validation.SingUpValidation

import com.example.initialization_impl.utils.validation.ValidationResult
import javax.inject.Inject

class ValidateGenderUseCase @Inject constructor() {
    operator fun invoke(gender: String): ValidationResult {
        if (gender.isBlank()) {
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