package com.example.fefufit.domain.use_cases.initial.validation.sing_in_validation

import android.util.Patterns
import com.example.fefufit.domain.models.validation_models.ValidationResult
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