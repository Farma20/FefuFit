package com.example.sing_in_impl.utils.validation.SingUpValidation

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