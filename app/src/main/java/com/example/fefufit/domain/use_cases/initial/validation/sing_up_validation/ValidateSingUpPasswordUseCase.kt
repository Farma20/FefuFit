package com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation

import com.example.fefufit.domain.models.validation_models.ValidationResult
import javax.inject.Inject

class ValidateSingUpPasswordUseCase @Inject constructor() {
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