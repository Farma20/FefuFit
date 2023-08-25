package com.example.fefufit.navigation

import com.example.main_api.MainPageApi
import com.example.initialization_api.InitializationApi
import com.example.sing_up_api.SingUpApi

//interface with all screens
interface FeatureApiHolder {

    val singInScreen: InitializationApi

    val singUpScreen: SingUpApi

    val mainScreen: MainPageApi
}