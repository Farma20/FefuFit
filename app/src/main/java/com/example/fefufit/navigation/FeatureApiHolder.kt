package com.example.fefufit.navigation

import com.example.initialization_api.InitializationApi
import com.example.main_api.MainPageApi
import com.example.timetable_api.TimeTableApi

//interface with all screens
interface FeatureApiHolder {

    val singInScreen: InitializationApi

    val mainScreen: MainPageApi

    val timetableScreen:TimeTableApi
}