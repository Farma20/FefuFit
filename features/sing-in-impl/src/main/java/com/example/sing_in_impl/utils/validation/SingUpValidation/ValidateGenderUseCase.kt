package com.example.sing_in_impl.utils.validation.SingUpValidation

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