package com.example.fefufit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.feature_api.register

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    featureApiHolder: FeatureApiHolder,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = featureApiHolder.mainScreen.route
//        startDestination = featureApiHolder.singInScreen.route
    ){
        register(
            featureApiHolder.singInScreen,
            navController,
            modifier
        )
        register(
            featureApiHolder.mainScreen,
            navController,
            modifier
        )
    }
}