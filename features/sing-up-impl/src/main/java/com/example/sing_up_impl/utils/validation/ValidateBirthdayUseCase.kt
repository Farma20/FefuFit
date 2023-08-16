package com.example.sing_up_impl.utils.validation

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