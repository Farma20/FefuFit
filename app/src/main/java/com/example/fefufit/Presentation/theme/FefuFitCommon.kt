package com.example.fefufit.Presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class FefuFitColors{
    lateinit var mainAppColors: MainAppColors
    lateinit var textColor: TextColor
    lateinit var elementsColor: ElementsColor

    //main colors
    data class MainAppColors(
        val appBackgroundColor: Color,
        val appBottomNavColor:Color,
        val appCardColor:Color,
        val appBlueColor:Color,
    )

    data class TextColor(
        val mainTextColor:Color,
        val secondaryTextColor:Color,
    )

    data class ElementsColor(
        val elementColor:Color
    )
}

object FefuFitTheme{
    val color:FefuFitColors
    @Composable
    get() = LocalFefuFitColors.current

    val LocalFefuFitColors = staticCompositionLocalOf<FefuFitColors> {
        error("No colors provide")
    }
}