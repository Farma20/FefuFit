package com.example.fefufit.Presentation.Initialization.SingUpScreen.Navigation

sealed class SingUpFieldsScreensRoute(val route: String) {
    object SingUpFieldsFirst:SingUpFieldsScreensRoute("singUpFieldsFirst")
    object SingUpFieldsSecond:SingUpFieldsScreensRoute("singUpFieldsSecond")
}