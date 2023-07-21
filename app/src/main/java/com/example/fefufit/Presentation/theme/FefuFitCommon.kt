package com.example.fefufit.Presentation.theme

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