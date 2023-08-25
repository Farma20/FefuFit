package com.example.sing_in_impl.naigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.main_api.MainPageApi
import com.example.sing_in_api.SingInApi
import com.example.sing_in_impl.presentation.SingIn.SingInScreen
import com.example.sing_up_api.SingUpApi
import javax.inject.Inject
import javax.inject.Singleton

private const val GRAPH_ROUTE = "SingInPageGraph"


@Singleton
class SingInImpl @Inject constructor(
    private val mainPage:MainPageApi,
    private val singUpPage: SingUpApi
) :SingInApi {
    override val route = GRAPH_ROUTE
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route){
            SingInScreen(
                modifier = modifier,
                onNavigateToMain = {
                    navController.navigate(mainPage.route){
                        popUpTo(0)
                    }
                },
                onNavigateToSingUp = {
                    navController.navigate(singUpPage.route)
                }
            )
        }
    }
}
