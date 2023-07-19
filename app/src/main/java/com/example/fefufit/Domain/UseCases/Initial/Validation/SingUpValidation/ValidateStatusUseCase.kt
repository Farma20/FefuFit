package com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation

import android.util.Patterns
import com.example.fefufit.Domain.Models.ValidationModels.ValidationResult
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