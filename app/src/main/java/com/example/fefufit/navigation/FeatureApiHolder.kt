package com.example.fefufit.navigation

import com.example.main_api.MainPageApi
import com.example.sing_in_api.SingInApi
import com.example.sing_up_api.SingUpApi

//interface with all screens
interface FeatureApiHolder {

    val singInScreen: SingInApi

    val singUpScreen: SingUpApi

    val mainScreen: MainPageApi
}