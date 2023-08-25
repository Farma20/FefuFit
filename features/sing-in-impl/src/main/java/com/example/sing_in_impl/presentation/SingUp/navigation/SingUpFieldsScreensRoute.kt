package com.example.sing_in_impl.presentation.SingUp.navigation

sealed class SingUpFieldsScreensRoute(val route: String) {
    object SingUpFieldsFirst: SingUpFieldsScreensRoute("singUpFieldsFirst")
    object SingUpFieldsSecond: SingUpFieldsScreensRoute("singUpFieldsSecond")
}