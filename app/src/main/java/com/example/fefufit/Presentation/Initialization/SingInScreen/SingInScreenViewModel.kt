package com.example.fefufit.Presentation.Initialization.SingInScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fefufit.Domain.Models.ValidationModels.SingInFromState

class SingInScreenViewModel:ViewModel() {

    var state by mutableStateOf(SingInFromState())
}