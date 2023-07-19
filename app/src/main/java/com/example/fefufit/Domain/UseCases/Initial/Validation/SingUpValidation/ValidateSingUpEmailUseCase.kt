package com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation

import android.util.Patterns
import com.example.fefufit.Domain.Models.ValidationModels.ValidationResult
import javax.inject.Inject

class ValidateSingUpEmailUseCase @Inject constructor() {
    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Поле не должно быть пустым"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                success = false,
                errorMessage = "Неверная электронная почта"
            )
        }
        return ValidationResult(
            success = true
        )
    }
}