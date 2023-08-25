package com.example.initialization_impl.naigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.main_api.MainPageApi
import com.example.initialization_api.InitializationApi
import com.example.initialization_impl.presentation.SingIn.SingInScreen
import com.example.initialization_impl.presentation.SingUp.SingUpScreen
import javax.inject.Inject
import javax.inject.Singleton

private const val GRAPH_ROUTE = "initializationPageGraph"
private const val SING_IN_ROUTE = "singInRoute"
private const val SING_UP_ROUTE = "singUpRoute"


@Singleton
class InitializationImpl @Inject constructor(
    private val mainPage:MainPageApi,
) :InitializationApi {
    override val route = GRAPH_ROUTE
    @RequiresApi(Build.VERSION_CODES.O)
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = route,
            startDestination = SING_IN_ROUTE
        ){
            composable(SING_IN_ROUTE) {
                SingInScreen(
                    modifier = modifier,
                    onNavigateToMain = {
                        navController.navigate(mainPage.route) {
                            popUpTo(0)
                        }
                    },
                    onNavigateToSingUp = {
                        navController.navigate(SING_UP_ROUTE)
                    }
                )
            }
            composable(SING_UP_ROUTE) {
                SingUpScreen(
                    modifier = modifier,
                    onNavigateToMain = {
                        navController.navigate(mainPage.route){
                            popUpTo(0)
                        }
                    },
                    onNavigateToSingIn = { navController.popBackStack() }
                )
            }
        }
    }
}
