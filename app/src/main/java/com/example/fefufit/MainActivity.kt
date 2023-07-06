package com.example.fefufit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fefufit.Presentation.Navigation.MainScreens
import com.example.fefufit.Presentation.theme.FefuFitTheme

class MainActivity : ComponentActivity() {

    private val viewModel:MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //installing splashScreen
        installSplashScreen().apply {
            setKeepVisibleCondition{
                viewModel.isLoading.value
            }
        }

        setContent {
            FefuFitTheme {
                Surface {
                    //AppNavigation
                    MainScreens()
                }
            }
        }
    }
}
