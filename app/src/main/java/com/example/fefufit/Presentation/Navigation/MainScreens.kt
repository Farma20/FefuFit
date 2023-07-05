package com.example.fefufit.Presentation.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fefufit.Presentation.Initialization.SingInScreen.SingInScreen
import com.example.fefufit.Presentation.SplashScreen.SplashScreen

@Composable
fun MainScreens() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainScreensRout.SplashScreen.route){
        composable(route = MainScreensRout.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(route = MainScreensRout.SingInScreen.route){
            SingInScreen(navController)
        }
        composable(route = MainScreensRout.SingUpScreen.route){

        }
    }
}