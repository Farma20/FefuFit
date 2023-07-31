package com.example.fefufit.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.fefufit.presentation.theme.FefuFitTheme.LocalFefuFitColors

@Composable
fun FefuFitTheme(
    blackTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors: FefuFitColors = when(blackTheme){
        true -> blackThemeAppPalette
        false -> whiteThemeAppPalette
    }

    CompositionLocalProvider(
        LocalFefuFitColors provides colors,
        content = content
    )
}