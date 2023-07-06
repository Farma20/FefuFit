package com.example.fefufit.Presentation.Initialization.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fefufit.Presentation.Initialization.SingInScreen.SingInScreen

@Composable
fun InitializationScreens() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = InitializationScreensRout.SingInScreen.route){
        composable(route = InitializationScreensRout.SingInScreen.route){
            SingInScreen(navController)
        }
        composable(route = InitializationScreensRout.SingUpScreen.route){

        }
    }
}