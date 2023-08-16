package com.example.sing_up_impl.presentation.navigation

sealed class InputFieldsStates{
    object FirstInputFields:InputFieldsStates()
    object SecondInputFields:InputFieldsStates()
}