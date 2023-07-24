package com.example.fefufit.Presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.dataStore
import com.example.fefufit.Data.Internal.DataStore.DataStoreManager
import com.example.fefufit.Data.Internal.DataStore.Entities.AppInternalData
import com.example.fefufit.Data.Internal.DataStore.Entities.UserMetaData
import com.example.fefufit.Data.Internal.DataStore.Serializer.AppInternalSerializer
import com.example.fefufit.Presentation.Initialization.Navigation.InitializationScreens
import com.example.fefufit.Presentation.theme.FefuFitTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //installing splashScreen
        installSplashScreen().apply {
            setKeepVisibleCondition{
                viewModel.isLoading.value
            }
        }

        //SOFT_INPUT_KEYBOARD
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            val isDarkTheme = isSystemInDarkTheme()
            FefuFitTheme(isDarkTheme) {

                //painted system controllers
                val systemUiController = rememberSystemUiController()
                val barBackground = FefuFitTheme.color.mainAppColors.appBackgroundColor

                //painted system upp & bottom panels
                SideEffect {
                    systemUiController.setStatusBarColor(color = barBackground, darkIcons = !isDarkTheme)
                    systemUiController.setNavigationBarColor(color = barBackground)
                }

                Surface {
                    //InitializationNavigation
                    InitializationScreens()
                }
            }
        }
    }
}
