package com.example.fefufit.Presentation.Initialization.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fefufit.Domain.UseCases.Initial.SingInUseCase
import com.example.fefufit.Domain.UseCases.Initial.SingUpUseCase
import com.example.fefufit.Presentation.Initialization.SingInScreen.SingInScreen
import com.example.fefufit.Presentation.Initialization.SingUpScreen.SingUpScreen

@Composable
fun InitializationScreens(singInUseCase: SingInUseCase, singUpUseCase: SingUpUseCase) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = InitializationScreensRoute.SingInScreen.route){
        composable(route = InitializationScreensRoute.SingInScreen.route){
            SingInScreen(navController, singInUseCase)
        }
        composable(route = InitializationScreensRoute.SingUpScreen.route){
            SingUpScreen(navController, singUpUseCase)
        }
    }
}