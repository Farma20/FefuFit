package com.example.fefufit.Presentation.Initialization.SingUpScreen.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Elements.SingUpFieldsFirst
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Elements.SingUpFieldsSecond
import com.example.fefufit.Presentation.Initialization.SingUpScreen.SingUpScreenViewModel

@Composable
fun SingUpFieldsScreens(viewModel: SingUpScreenViewModel) {
    val navController = rememberAnimatedNavController()
    NavHost(navController = navController, startDestination = SingUpFieldsScreensRoute.SingUpFieldsFirst.route){
        viewModel.getFieldsNavController(navController = navController)
        composable(route = SingUpFieldsScreensRoute.SingUpFieldsFirst.route){
            SingUpFieldsFirst(viewModel = viewModel)
        }
        composable(route = SingUpFieldsScreensRoute.SingUpFieldsSecond.route){
            SingUpFieldsSecond(viewModel = viewModel)
        }
    }
}