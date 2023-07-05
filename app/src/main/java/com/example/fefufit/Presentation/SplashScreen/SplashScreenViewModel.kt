package com.example.fefufit.Presentation.SplashScreen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.fefufit.Presentation.Navigation.MainScreens
import com.example.fefufit.Presentation.Navigation.MainScreensRout

class SplashScreenViewModel:ViewModel() {
    private var navController: NavController? = null

    fun getNavController(controller: NavController){
        navController = controller
    }

    fun nextScreen(){
        navController!!.navigate(MainScreensRout.SingInScreen.route){
            popUpTo(0)
        }
    }
}