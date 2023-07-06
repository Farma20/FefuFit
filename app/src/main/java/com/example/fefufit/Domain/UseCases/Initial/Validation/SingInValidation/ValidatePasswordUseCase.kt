package com.example.fefufit.Domain.UseCases.Initial.Validation.SingInValidation

import android.util.Patterns
import com.example.fefufit.Domain.Models.ValidationModels.ValidationResult

class ValidatePasswordUseCase {

    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
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