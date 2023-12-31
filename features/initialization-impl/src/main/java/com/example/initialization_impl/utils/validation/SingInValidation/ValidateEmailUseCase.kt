package com.example.initialization_impl.utils.validation.SingInValidation

import android.util.Patterns
import com.example.initialization_impl.utils.validation.ValidationResult
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