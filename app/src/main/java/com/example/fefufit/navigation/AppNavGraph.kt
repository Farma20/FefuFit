package com.example.fefufit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import com.example.sing_in_impl.naigation.SingInImpl

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    featureApiHolder: FeatureApiHolder,
    navController: NavController
) {
    NavHost(
        navController = ,
        graph = SingInImpl
    )
}