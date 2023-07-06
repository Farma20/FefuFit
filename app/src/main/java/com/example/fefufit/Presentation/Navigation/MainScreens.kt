package com.example.fefufit.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fefufit.Presentation.Initialization.SingInScreen.SingInScreen

@Composable
fun MainScreens() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainScreensRout.SingInScreen.route){
        composable(route = MainScreensRout.SingInScreen.route){
            SingInScreen(navController)
        }
        composable(route = MainScreensRout.SingUpScreen.route){

        }
    }
}