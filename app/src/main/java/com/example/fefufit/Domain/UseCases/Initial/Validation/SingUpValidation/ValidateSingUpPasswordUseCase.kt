package com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation

import com.example.fefufit.Domain.Models.ValidationModels.ValidationResult

class ValidateSingUpPasswordUseCase {
    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Поле не должно быть пустым"
            )
        }
//        if(password.length < 8){
//            return ValidationResult(
//                success = false,
//                errorMessage = "Пароль должен содержать хотя бы 8 символов"
//            )
//        }
//
//        val constantsLettersAndDigits = password.any { it.isDigit() } &&
//                password.any { it.isLetter() }
//
//        if(!constantsLettersAndDigits){
//            return ValidationResult(
//                success = false,
//                errorMessage = "Пароль должен содержать хотя бы одну цифру и букву"
//            )
//        }
        return ValidationResult(
            success = true
        )
    }
}