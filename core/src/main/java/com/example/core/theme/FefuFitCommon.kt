package com.example.core.theme

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
        val errorColor: Color
    )

    data class TextColor(
        val mainTextColor:Color,
        val whiteTextColor: Color,
        val secondaryTextColor:Color,
        val tertiaryTextColor: Color,
        val setTextColor: Color
    )

    data class ElementsColor(
        val elementColor:Color,
        val onElementsColor: Color,
        val setColor:Color
    )
}

object FefuFitTheme{
    val color: FefuFitColors
    @Composable
    get() = LocalFefuFitColors.current

    val LocalFefuFitColors = staticCompositionLocalOf<FefuFitColors> {
        error("No colors provide")
    }
}