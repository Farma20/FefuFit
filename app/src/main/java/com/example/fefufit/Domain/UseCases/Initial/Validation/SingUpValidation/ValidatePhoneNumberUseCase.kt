package com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation

import android.util.Patterns
import com.example.fefufit.Domain.Models.ValidationModels.ValidationResult

class ValidatePhoneNumberUseCase {
    operator fun invoke(phoneNumber: String): ValidationResult {
        if (phoneNumber.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Поле не должно быть пустым"
            )
        }
        if(!(phoneNumber.length == 12 && phoneNumber[0] == '+')&&(phoneNumber.length != 11)){
            return ValidationResult(false, "Номер телефона должен состоять из 11 символов, не учитывая символ '+'")
        }
        return ValidationResult(
            success = true
        )
    }
}