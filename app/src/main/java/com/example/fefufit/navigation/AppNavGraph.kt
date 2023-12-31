package com.example.fefufit.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.theme.FefuFitTheme
import com.example.feature_api.register

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    featureApiHolder: FeatureApiHolder,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
//        startDestination = BottomTabs.Calendar.route,
//        startDestination = BottomTabs.Main.route,
        startDestination = featureApiHolder.singInScreen.route,
        modifier = Modifier.background(FefuFitTheme.color.mainAppColors.appBackgroundColor)
    ){
        register(
            featureApiHolder.singInScreen,
            navController,
            modifier
        )

        navigation(
            route = BottomTabs.Main.route,
            startDestination = featureApiHolder.mainScreen.route
        ){
            register(
                featureApiHolder.mainScreen,
                navController,
                modifier
            )
        }

        navigation(
            route = BottomTabs.Calendar.route,
            startDestination =featureApiHolder.timetableScreen.route
        ){
            register(
                featureApiHolder.timetableScreen,
                navController,
                modifier
            )
        }

        navigation(
            route = BottomTabs.Services.route,
            startDestination = "services"
        ){
            composable("services"){
                Scaffold(
                    containerColor = FefuFitTheme.color.mainAppColors.appBackgroundColor
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = "Абонементы в разработке")
                    }
                }
            }
        }
    }
}