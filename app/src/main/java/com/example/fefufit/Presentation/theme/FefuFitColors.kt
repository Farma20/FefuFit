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
        tertiaryTextColor = Color(0xFF252525),
        setTextColor = Color(0xFF252525)
    )

    this.elementsColor = FefuFitColors.ElementsColor(
        elementColor = Color(0xFF4343F4),
        onElementsColor = Color(0xFFFFFFFF),
        setColor = Color(0xFF4343F4)
    )
}

val blackThemeAppPalette = FefuFitColors().apply {
    this.mainAppColors = FefuFitColors.MainAppColors(
        appBackgroundColor = Color(0xFF252525),
        appBottomNavColor = Color(0xFFDFDFDF),
        appBlueColor = Color(0xFF4343F4),
        appCardColor = Color(0xFF515151),
        errorColor = Color(0xFFD90000)
    )

    this.textColor = FefuFitColors.TextColor(
        mainTextColor = Color(0xFFFFFFFF),
        secondaryTextColor = Color(0xFFFFFFFF),
        tertiaryTextColor = Color(0xFFF1F1F1),
        setTextColor = Color(0xFF252525)
    )

    this.elementsColor = FefuFitColors.ElementsColor(
        elementColor = Color(0xFFFFFFFF),
        onElementsColor = Color(0xFF252525),
        setColor = Color(0xFF252525)
    )
}