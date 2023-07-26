package com.example.fefufit.presentation.initialization.sing_up_screen.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import com.example.fefufit.presentation.initialization.sing_up_screen.elements.SingUpFieldsFirst
import com.example.fefufit.presentation.initialization.sing_up_screen.elements.SingUpFieldsSecond
import com.example.fefufit.presentation.initialization.sing_up_screen.SingUpScreenViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SingUpFieldsScreens(viewModel: SingUpScreenViewModel) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = SingUpFieldsScreensRoute.SingUpFieldsFirst.route){
        viewModel.getFieldsNavController(navController = navController)
        composable(
            route = SingUpFieldsScreensRoute.SingUpFieldsFirst.route,
            exitTransition = {
                slideOutHorizontally (
                    targetOffsetX = {-1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popEnterTransition = {
                slideInHorizontally (
                    initialOffsetX = {-1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
        ){
            SingUpFieldsFirst(viewModel = viewModel)
        }
        composable(
            route = SingUpFieldsScreensRoute.SingUpFieldsSecond.route,
            enterTransition = {
                slideInHorizontally (
                    initialOffsetX = {1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popExitTransition = {
                slideOutHorizontally (
                    targetOffsetX = {1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
        ){
            SingUpFieldsSecond(viewModel = viewModel)
        }
    }
}