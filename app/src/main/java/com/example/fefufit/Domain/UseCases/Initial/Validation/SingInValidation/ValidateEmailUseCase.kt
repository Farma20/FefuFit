package com.example.fefufit.Domain.UseCases.Initial.Validation.SingInValidation

import android.util.Patterns
import com.example.fefufit.Domain.Models.ValidationModels.ValidationResult
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {

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
                errorMessage = "Неккоректный почтовый адрес"
            )
        }
        return ValidationResult(
            success = true
        )
    }
}