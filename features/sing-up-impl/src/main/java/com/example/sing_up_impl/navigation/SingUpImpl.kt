package com.example.sing_up_impl.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.sing_up_api.SingUpApi
import com.example.sing_up_impl.presentation.SingUpScreen
import javax.inject.Inject
import javax.inject.Singleton

private const val GRAPH_ROUTE = "SingUpPageGraph"

@Singleton
class SingUpImpl @Inject constructor() : SingUpApi {
    override val route = GRAPH_ROUTE

    @RequiresApi(Build.VERSION_CODES.O)
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            SingUpScreen(
                modifier = modifier,
                onNavigateToSingIn = { navController.popBackStack() }
            )
        }
    }
}