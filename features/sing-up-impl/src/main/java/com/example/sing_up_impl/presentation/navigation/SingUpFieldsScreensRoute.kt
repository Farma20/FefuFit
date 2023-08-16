package com.example.sing_up_impl.presentation.navigation

sealed class SingUpFieldsScreensRoute(val route: String) {
    object SingUpFieldsFirst:SingUpFieldsScreensRoute("singUpFieldsFirst")
    object SingUpFieldsSecond:SingUpFieldsScreensRoute("singUpFieldsSecond")
}