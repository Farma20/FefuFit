package com.example.fefufit.Presentation.Initialization.SingUpScreen

import android.annotation.SuppressLint
import android.graphics.Paint.Style
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefufit.Domain.UseCases.Initial.SingUpUseCase
import com.example.fefufit.Presentation.Initialization.SingInScreen.SingInScreenViewModel
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Navigation.InputFieldsStates
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Navigation.SingUpFieldsScreens
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Navigation.SingUpFieldsScreensRoute
import com.example.fefufit.Presentation.theme.BlackApp
import com.example.fefufit.Presentation.theme.BlueApp
import com.example.fefufit.Presentation.theme.WhiteApp
import com.example.fefufit.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpScreen(singUpUseCase:SingUpUseCase) {

    val viewModel: SingUpScreenViewModel = SingUpScreenViewModel(singUpUseCase = singUpUseCase)

    //painted system controllers
    val systemUiController = rememberSystemUiController()
    val barBackground = WhiteApp

    //painted system upp & bottom panels
    SideEffect {
        systemUiController.setStatusBarColor(color = barBackground, darkIcons = true)
        systemUiController.setNavigationBarColor(color = barBackground)
    }

    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    //page variables
    var labelText by remember { mutableStateOf("Личные данные") }
    var circlePagerColor by remember { mutableStateOf(Color.Transparent) }
    var circlePagerTextColor by remember { mutableStateOf(BlueApp) }

    when(viewModel.pageState){
        InputFieldsStates.FirstInputFields -> {
            labelText = "Личные данные"
            circlePagerColor = Color.Transparent
            circlePagerTextColor = BlueApp
        }
        InputFieldsStates.SecondInputFields -> {
            labelText = "Учетные данные"
            circlePagerColor = BlueApp
            circlePagerTextColor = WhiteApp
        }
    }

    LaunchedEffect(key1 = context){
        viewModel.validationFirstEvents.collect{event ->
            when(event){
                is SingUpScreenViewModel.ValidationEvent.SuccessFirst ->{
                    viewModel.inputFieldsNavController!!.navigate(SingUpFieldsScreensRoute.SingUpFieldsSecond.route)
                }
                is SingUpScreenViewModel.ValidationEvent.SuccessSecond->{
                    snackBarHostState.showSnackbar(
                        message = "Success"
                    )
                }
                is SingUpScreenViewModel.ValidationEvent.Error ->{
                    snackBarHostState.showSnackbar(
                        message = viewModel.errorData
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState)},
        topBar = {
            UppBar(modifier = Modifier)
        }
    ) {scaffoldPadding->
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = WhiteApp
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(scaffoldPadding.calculateTopPadding()))
                RegistrationPager(circlePagerColor, circlePagerTextColor)
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = labelText,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(600),
                    color = BlackApp,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(24.dp))

                SingUpFieldsScreens(viewModel)

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UppBar(modifier: Modifier){
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Регистрация",
                fontSize = 22.sp,
                fontWeight = FontWeight(400),
                color = BlackApp
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.back_arrow),
                    tint = BlueApp,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}

@Composable
private fun RegistrationPager(
    circlePagerColor: Color,
    circlePagerTextColor: Color
){
    Row() {
        Box(
            modifier = Modifier
                .border(1.dp, BlueApp, CircleShape)
                .size(32.dp)
                .background(BlueApp, CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "1",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 22.sp,
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .border(1.dp, BlueApp, CircleShape)
                .size(32.dp)
                .background(circlePagerColor, CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "2",
                color = circlePagerTextColor,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 22.sp,
            )
        }
    }
}


