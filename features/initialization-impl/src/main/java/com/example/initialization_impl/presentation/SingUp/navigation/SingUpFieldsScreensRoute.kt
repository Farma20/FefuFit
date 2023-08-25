package com.example.initialization_impl.presentation.SingUp.navigation

sealed class SingUpFieldsScreensRoute(val route: String) {
    object SingUpFieldsFirst: SingUpFieldsScreensRoute("singUpFieldsFirst")
    object SingUpFieldsSecond: SingUpFieldsScreensRoute("singUpFieldsSecond")
}