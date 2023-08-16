package com.example.sing_in_impl.naigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sing_in_api.SingInApi
import com.example.sing_in_impl.presentation.SingInScreen
import javax.inject.Inject
import javax.inject.Singleton

private const val GRAPH_ROUTE = "SingInPageGraph"
private const val SING_IN_ROUTE = "SingInPageRoute"

@Singleton
class SingInImpl @Inject constructor() :SingInApi {
    override val route = GRAPH_ROUTE
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = route,
            startDestination = SING_IN_ROUTE
        ){
            composable(SING_IN_ROUTE){
                SingInScreen()
            }
        }
    }
}