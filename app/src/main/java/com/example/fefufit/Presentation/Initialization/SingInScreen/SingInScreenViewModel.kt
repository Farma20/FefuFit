package com.example.fefufit.Presentation.Initialization.SingInScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fefufit.Domain.Models.ValidationModels.SingInFromState
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingInValidation.ValidateEmailUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingInValidation.ValidatePasswordUseCase

class SingInScreenViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase = ValidateEmailUseCase(),
    private val validatePasswordUseCase: ValidatePasswordUseCase = ValidatePasswordUseCase()
):ViewModel() {

    var state by mutableStateOf(SingInFromState())


}