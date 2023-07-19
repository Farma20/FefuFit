package com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation

import android.util.Patterns
import com.example.fefufit.Domain.Models.ValidationModels.ValidationResult
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