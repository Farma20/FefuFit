package com.example.timetable_impl.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.magnifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.theme.FefuFitTheme
import com.example.timetable_api.TimeTableApi
import com.example.timetable_impl.presentation.timetable_screen.TimeTableScreen
import javax.inject.Inject
import javax.inject.Singleton

private const val GRAPH_ROUTE = "timeTablePageGraph"
private const val TIMETABLE_ROUTE = "timeTablePageRoute"
@Singleton
class TimeTableImpl @Inject constructor():TimeTableApi {
    override val route: String = GRAPH_ROUTE

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = route,
            startDestination = TIMETABLE_ROUTE
        ){
            composable(TIMETABLE_ROUTE){
                Scaffold(
                    containerColor = FefuFitTheme.color.mainAppColors.appBackgroundColor
                ) {
                    TimeTableScreen(
                        modifier = modifier
                    )
                }
            }
        }
    }
}