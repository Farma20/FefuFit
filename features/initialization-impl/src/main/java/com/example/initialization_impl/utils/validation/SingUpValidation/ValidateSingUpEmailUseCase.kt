package com.example.initialization_impl.utils.validation.SingUpValidation

import android.util.Patterns
import com.example.initialization_impl.utils.validation.ValidationResult
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