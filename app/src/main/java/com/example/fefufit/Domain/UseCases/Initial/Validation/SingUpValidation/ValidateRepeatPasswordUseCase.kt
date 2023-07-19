package com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation

import com.example.fefufit.Domain.Models.ValidationModels.ValidationResult
import javax.inject.Inject

class ValidateRepeatPasswordUseCase @Inject constructor() {
    operator fun invoke(password: String, repeatPassword: String): ValidationResult {
        if (repeatPassword.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Поле не должно быть пустым"
            )
        }
        if (password != repeatPassword){
            return ValidationResult(
                success = false,
                errorMessage = "Пароли не совпадают"
            )
        }
        return ValidationResult(
            success = true
        )
    }
}