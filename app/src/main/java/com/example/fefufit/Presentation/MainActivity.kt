package com.example.fefufit.Presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fefufit.Presentation.Initialization.Navigation.InitializationScreens
import com.example.fefufit.Presentation.theme.FefuFitTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //installing splashScreen
        installSplashScreen().apply {
            setKeepVisibleCondition{
                viewModel.isLoading.value
            }
        }
        val blackTheme by mutableStateOf(false)
        setContent {
            //painted system controllers
            val systemUiController = rememberSystemUiController()
            val barBackground = FefuFitTheme.color.mainAppColors.appBackgroundColor

            //painted system upp & bottom panels
            SideEffect {
                systemUiController.setStatusBarColor(color = barBackground, darkIcons = true)
                systemUiController.setNavigationBarColor(color = barBackground)
            }

            FefuFitTheme(blackTheme) {
                Surface {
                    //InitializationNavigation
                    InitializationScreens()
                }
            }
        }
    }
}
