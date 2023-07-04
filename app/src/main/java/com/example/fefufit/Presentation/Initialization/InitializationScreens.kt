package com.example.fefufit.Presentation.Initialization

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fefufit.Presentation.Initialization.Navigation.InitializationScreensRout

@Composable
fun InitializationScreens() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = InitializationScreensRout.SplashScreen.route){
        composable(route = InitializationScreensRout.SplashScreen.route){

        }
        composable(route = InitializationScreensRout.SingInScreen.route){

        }
        composable(route = InitializationScreensRout.SingUpScreen.route){

        }
    }
}