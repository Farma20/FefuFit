package com.example.fefufit.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.core.theme.FefuFitTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @SuppressLint("CoroutineCreationDuringComposition", "FlowOperatorInvokedInComposition")
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
                val scope = rememberCoroutineScope()

//                scope.launch {
//                    dataStoreManager.setUserMetaData(UserMetaData())
//                }

//                bookingUseCase().onEach {
//                    when(it){
//                        is Resource.Loading ->{
//                            println("loading")
//                        }
//                        is Resource.Success ->{
//                            println(it.data)
//                        }
//                        is Resource.Error ->{
//                            println(it.message)
//                        }
//                    }
//                }.launchIn(scope)


                //painted system controllers
                val systemUiController = rememberSystemUiController()
                val barBackground =
                    FefuFitTheme.color.mainAppColors.appBackgroundColor

                //painted system upp & bottom panels
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = barBackground,
                        darkIcons = !isDarkTheme
                    )
                    systemUiController.setNavigationBarColor(color = barBackground)
                }

                AppContent(viewModel = viewModel)
            }
        }
    }


}
