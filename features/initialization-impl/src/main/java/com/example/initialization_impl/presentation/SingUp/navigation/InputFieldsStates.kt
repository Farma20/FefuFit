package com.example.initialization_impl.presentation.SingUp.navigation

sealed class InputFieldsStates{
    object FirstInputFields: InputFieldsStates()
    object SecondInputFields: InputFieldsStates()
}