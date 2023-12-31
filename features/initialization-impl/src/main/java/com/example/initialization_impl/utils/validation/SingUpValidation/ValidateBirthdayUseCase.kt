package com.example.initialization_impl.utils.validation.SingUpValidation

import com.example.initialization_impl.utils.validation.ValidationResult
import javax.inject.Inject

class ValidateBirthdayUseCase @Inject constructor() {
    operator fun invoke(birthday: String): ValidationResult {
        if (birthday.isBlank()) {
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