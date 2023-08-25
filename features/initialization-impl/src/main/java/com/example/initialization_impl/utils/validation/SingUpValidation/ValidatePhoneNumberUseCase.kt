package com.example.initialization_impl.utils.validation.SingUpValidation

import javax.inject.Inject

class ValidatePhoneNumberUseCase @Inject constructor() {
    operator fun invoke(phoneNumber: String): ValidationResult {
        if (phoneNumber.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Поле не должно быть пустым"
            )
        }
        if(!(phoneNumber.length == 12 && phoneNumber[0] == '+')&&(phoneNumber.length != 11)){
            return ValidationResult(
                success = false,
                errorMessage = "Номер телефона должен состоять из 11 символов, не учитывая символ '+'"
            )
        }
        return ValidationResult(
            success = true
        )
    }
}