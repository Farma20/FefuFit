package com.example.fefufit.Presentation.Navigation

sealed class MainScreensRout(val route:String) {
    object SplashScreen: MainScreensRout("singInScreen")
    object SingInScreen: MainScreensRout("singInScreen")
    object SingUpScreen: MainScreensRout("singUpScreen")
}