package com.example.fefufit.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.core.theme.FefuFitTheme
import com.example.fefufit.navigation.AppNavGraph
import com.example.fefufit.navigation.BottomTabs


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(viewModel: MainActivityViewModel) {

    val navController = rememberNavController()
    val tabs = remember { BottomTabs.values() }

    Scaffold(
        modifier = Modifier
            .background(FefuFitTheme.color.mainAppColors.appBackgroundColor)
        ,
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            AppNavGraph(
                modifier = Modifier.height(84.dp),
                featureApiHolder = viewModel,
                navController = navController,
            )
            BottomNavBar(navController = navController, tabItems = tabs)
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController, tabItems: Array<BottomTabs>) {


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomTabsRoutes = remember { BottomTabs.values().map { it.route } }

    val showBottomTabs = currentDestination?.hierarchy?.any { destination ->
        destination.route in bottomTabsRoutes
    } == true

    if (showBottomTabs) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(bottom = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            BottomNavigation(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .clip(RoundedCornerShape(16)),
                backgroundColor = FefuFitTheme.color.mainAppColors.appBottomNavColor,
                elevation = 0.dp
            ) {
                tabItems.forEach { tab ->
                    val isTabSelected =
                        currentDestination?.hierarchy?.any { it.route == tab.route } == true

                    BottomNavigationItem(
                        icon = {
                            Row {
                                Icon(
                                    painter = painterResource(id = tab.iconRes),
                                    contentDescription = null,
                                    modifier = Modifier.padding(bottom = 3.dp, end = 0.dp)
                                )
                            }
                        },
                        selected = isTabSelected,
                        selectedContentColor = FefuFitTheme.color.textColor.setTextColor,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
            }
        }
    }
}