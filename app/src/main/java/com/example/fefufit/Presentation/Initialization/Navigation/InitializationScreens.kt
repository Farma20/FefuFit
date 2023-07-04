package com.example.fefufit.Presentation.Initialization.Navigation

sealed class InitializationScreens(val rout:String) {
    object SplashScreen: InitializationScreens("splashScreen")
    object SingInScreen: InitializationScreens("singInScreen")
    object SingUpScreen: InitializationScreens("singUpScreen")
}