package com.example.fefufit.Presentation.Initialization.Navigation

sealed class InitializationScreensRoute(val route:String) {
    object SingInScreen: InitializationScreensRoute("singInScreen")
    object SingUpScreen: InitializationScreensRoute("singUpScreen")
}