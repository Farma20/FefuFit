package com.example.fefufit.presentation.initialization.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fefufit.presentation.initialization.sing_in_screen.SingInScreen
import com.example.fefufit.presentation.initialization.sing_up_screen.SingUpScreen

@Composable
fun InitializationScreens() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = InitializationScreensRoute.SingInScreen.route){
        composable(route = InitializationScreensRoute.SingInScreen.route){
            SingInScreen(navController)
        }
        composable(route = InitializationScreensRoute.SingUpScreen.route){
            SingUpScreen(navController)
        }
    }
}