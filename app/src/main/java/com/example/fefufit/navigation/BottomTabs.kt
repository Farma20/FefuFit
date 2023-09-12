package com.example.fefufit.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.fefufit.R

enum class BottomTabs(
    val route: String,
    @DrawableRes val iconRes: Int
) {
    Main("MainTab", R.drawable.home),
    Calendar("CalendarTab", R.drawable.calendar),
    Services("ServicesTab", R.drawable.services),
}