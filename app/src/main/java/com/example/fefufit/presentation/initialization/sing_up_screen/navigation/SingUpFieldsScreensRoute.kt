package com.example.fefufit.presentation.initialization.sing_up_screen.navigation

sealed class SingUpFieldsScreensRoute(val route: String) {
    object SingUpFieldsFirst:SingUpFieldsScreensRoute("singUpFieldsFirst")
    object SingUpFieldsSecond:SingUpFieldsScreensRoute("singUpFieldsSecond")
}