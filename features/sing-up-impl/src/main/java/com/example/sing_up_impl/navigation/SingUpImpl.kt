package com.example.sing_up_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sing_up_api.SingUpApi
import com.example.sing_up_impl.presentation.SingUpScreen
import javax.inject.Inject
import javax.inject.Singleton

private const val GRAPH_ROUTE = "SingUpPageGraph"
private const val SING_UP_ROUTE = "SingUpPageRoute"
@Singleton
class SingUpImpl @Inject constructor(): SingUpApi {
    override val route = GRAPH_ROUTE

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = route,
            startDestination = SING_UP_ROUTE
        ){
            composable(SING_UP_ROUTE){
                SingUpScreen()
            }
        }
    }

}