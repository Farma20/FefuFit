package com.example.fefufit.Presentation.Initialization.Navigation

sealed class InitializationScreensRout(val route:String) {
    object SplashScreen: InitializationScreensRout("splashScreen")
    object SingInScreen: InitializationScreensRout("singInScreen")
    object SingUpScreen: InitializationScreensRout("singUpScreen")
}