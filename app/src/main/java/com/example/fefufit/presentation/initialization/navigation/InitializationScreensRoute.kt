package com.example.fefufit.presentation.initialization.navigation

sealed class InitializationScreensRoute(val route:String) {
    object SingInScreen: InitializationScreensRoute("singInScreen")
    object SingUpScreen: InitializationScreensRoute("singUpScreen")
}