package com.example.fefufit.Presentation.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fefufit.Presentation.theme.BlueApp
import com.example.fefufit.Presentation.theme.WhiteApp
import com.example.fefufit.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel = viewModel()
) {

    viewModel.getNavController(navController)

    LaunchedEffect(Unit){
        delay(2000)
        viewModel.nextScreen()
    }

    //painted system controllers
    val systemUiController = rememberSystemUiController()
    val barBackground = BlueApp

    //painted system upp & bottom panels
    SideEffect {
        systemUiController.setStatusBarColor(color = barBackground)
        systemUiController.setNavigationBarColor(color = barBackground)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = BlueApp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(169.dp, 183.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
            )
        }
    }
}