package com.example.fefufit.Presentation.theme

import androidx.compose.ui.graphics.Color

val whiteThemeAppPalette = FefuFitColors().apply {
    this.mainAppColors = FefuFitColors.MainAppColors(
        appBackgroundColor = Color(0xFFFFFFFF),
        appBottomNavColor = Color(0xFFDFDFDF),
        appBlueColor = Color(0xFF4343F4),
        appCardColor = Color(0xFFFFFFFF),
        errorColor = Color(0xFFD90000)
    )

    this.textColor = FefuFitColors.TextColor(
        mainTextColor = Color(0xFF252525),
        secondaryTextColor = Color(0xFF4343F4),
    )

    this.elementsColor = FefuFitColors.ElementsColor(
        elementColor = Color(0xFF4343F4)
    )
}