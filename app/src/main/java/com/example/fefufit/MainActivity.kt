package com.example.fefufit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fefufit.Data.Remote.Models.InitialModels.SingInDataModel
import com.example.fefufit.Data.Remote.Models.InitialModels.SingUpDataModel
import com.example.fefufit.Domain.UseCases.Initial.SingInUseCase
import com.example.fefufit.Domain.UseCases.Initial.SingUpUseCase
import com.example.fefufit.Presentation.Initialization.SingInScreen.SingInScreen
import com.example.fefufit.Presentation.Navigation.MainScreens
import com.example.fefufit.Presentation.SplashScreen.SplashScreen
import com.example.fefufit.Presentation.theme.BlueApp
import com.example.fefufit.Presentation.theme.FefuFitTheme
import com.example.fefufit.Utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
