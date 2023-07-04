package com.example.fefufit.Presentation.Initialization.Navigation

sealed class InitializationScreensRout(val rout:String) {
    object SplashScreen: InitializationScreensRout("splashScreen")
    object SingInScreen: InitializationScreensRout("singInScreen")
    object SingUpScreen: InitializationScreensRout("singUpScreen")
}