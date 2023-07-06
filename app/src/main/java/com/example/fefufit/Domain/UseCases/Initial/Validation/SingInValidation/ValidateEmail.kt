package com.example.fefufit.Domain.UseCases.Initial.Validation.SingInValidation

import android.util.Patterns
import com.example.fefufit.Domain.Models.ValidationModels.ValidationResult

class ValidateEmail {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Поле не должно быть пустым"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                success = false,
                errorMessage = "Некорректный почтовый адрес"
            )
        }
        return ValidationResult(
            success = true
        )
    }
}