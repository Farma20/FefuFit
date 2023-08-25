package com.example.sing_in_impl.utils.validation.SingInValidation

import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor(){

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