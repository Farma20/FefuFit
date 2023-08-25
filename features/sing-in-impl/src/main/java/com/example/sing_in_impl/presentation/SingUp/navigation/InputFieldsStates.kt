package com.example.sing_in_impl.presentation.SingUp.navigation

sealed class InputFieldsStates{
    object FirstInputFields: InputFieldsStates()
    object SecondInputFields: InputFieldsStates()
}