package com.example.fefufit.Presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fefufit.Domain.UseCases.Initial.SingInUseCase
import com.example.fefufit.Domain.UseCases.Initial.SingUpUseCase
import com.example.fefufit.FefuFitApp
import com.example.fefufit.Presentation.Initialization.Navigation.InitializationScreens
import com.example.fefufit.Presentation.Initialization.SingUpScreen.SingUpScreen
import com.example.fefufit.Presentation.theme.FefuFitTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var singInUseCase: SingInUseCase

    @Inject
    lateinit var singUpUseCase: SingUpUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as FefuFitApp).appComponent.inject(this)

        //installing splashScreen
        installSplashScreen().apply {
            setKeepVisibleCondition{
                viewModel.isLoading.value
            }
        }

        setContent {
            FefuFitTheme {
                Surface {
                    //InitializationNavigation
                    InitializationScreens(singInUseCase, singUpUseCase)
                }
            }
        }
    }
}
