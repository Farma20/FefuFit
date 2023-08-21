package com.example.fefufit.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.fefufit.navigation.AppNavGraph

@Composable
fun AppContent(viewModel: MainActivityViewModel) {
    val navController = rememberNavController()
    Scaffold() { innerPaddingModifier ->
        AppNavGraph(
            modifier = Modifier.padding(innerPaddingModifier),
            featureApiHolder = viewModel,
            navController = navController,
        )
    }
}