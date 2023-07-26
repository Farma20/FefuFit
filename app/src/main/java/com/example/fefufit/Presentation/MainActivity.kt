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
import com.example.fefufit.Domain.Repositorys.EventsRepository
import com.example.fefufit.Domain.Repositorys.ServicesRepository
import com.example.fefufit.Domain.Repositorys.UserDataRepository
import com.example.fefufit.Domain.UseCases.Main.UsersUseCases.UserShortDataUseCase
import com.example.fefufit.Presentation.Initialization.Navigation.InitializationScreens
import com.example.fefufit.Presentation.theme.FefuFitTheme
import com.example.fefufit.Utils.Resource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    @Inject
    lateinit var userUseCase:UserShortDataUseCase

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
            val userToken = dataStoreManager.data.collectAsState(initial = AppInternalData())

            val isDarkTheme = isSystemInDarkTheme()
            FefuFitTheme(isDarkTheme) {
                val scope = rememberCoroutineScope()

//                scope.launch {
//                    dataStoreManager.setUserMetaData(UserMetaData())
//                }

                userUseCase().onEach {
                    when(it){
                        is Resource.Loading ->{
                            println("loading")
                        }
                        is Resource.Success ->{
                            println(it.data)
                        }
                        is Resource.Error ->{
                            println(it.message)
                        }
                    }
                }.launchIn(scope)


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
