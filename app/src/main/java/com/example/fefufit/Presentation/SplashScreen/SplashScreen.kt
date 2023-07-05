package com.example.fefufit.Presentation.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fefufit.Presentation.theme.BlueApp
import com.example.fefufit.R

@Composable
fun SplashScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = BlueApp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
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