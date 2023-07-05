package com.example.fefufit.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fefufit.Presentation.SplashScreen.SplashScreen

@Composable
fun MainScreens() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainScreensRout.SplashScreen.route){
        composable(route = MainScreensRout.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(route = MainScreensRout.SingInScreen.route){

        }
        composable(route = MainScreensRout.SingUpScreen.route){

        }
    }
}