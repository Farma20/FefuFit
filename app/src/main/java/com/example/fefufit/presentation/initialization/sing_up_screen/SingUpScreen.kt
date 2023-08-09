package com.example.fefufit.presentation.initialization.sing_up_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fefufit.presentation.initialization.sing_up_screen.navigation.InputFieldsStates
import com.example.fefufit.presentation.initialization.sing_up_screen.navigation.SingUpFieldsScreens
import com.example.fefufit.presentation.initialization.sing_up_screen.navigation.SingUpFieldsScreensRoute
import com.example.core.theme.FefuFitTheme
import com.example.fefufit.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpScreen(navController: NavController) {

    val viewModel = hiltViewModel<SingUpScreenViewModel>()

    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    //page variables
    var labelText by remember { mutableStateOf("Личные данные") }
    var circlePagerColor by remember { mutableStateOf(Color.Transparent) }
    val background = com.example.core.theme.FefuFitTheme.color.elementsColor.onElementsColor
    var circlePagerTextColor by remember {
        mutableStateOf(background)
    }

    when(viewModel.pageState){
        InputFieldsStates.FirstInputFields -> {
            labelText = "Личные данные"
            circlePagerColor = Color.Transparent
            circlePagerTextColor = com.example.core.theme.FefuFitTheme.color.textColor.mainTextColor
        }
        InputFieldsStates.SecondInputFields -> {
            labelText = "Учетные данные"
            circlePagerColor = com.example.core.theme.FefuFitTheme.color.elementsColor.elementColor
            circlePagerTextColor = com.example.core.theme.FefuFitTheme.color.elementsColor.onElementsColor
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
            UppBar(modifier = Modifier, navController)
        }
    ) {scaffoldPadding->
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = com.example.core.theme.FefuFitTheme.color.mainAppColors.appBackgroundColor
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
                    color = com.example.core.theme.FefuFitTheme.color.textColor.mainTextColor,
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
private fun UppBar(modifier: Modifier, navController: NavController){
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Регистрация",
                fontSize = 22.sp,
                fontWeight = FontWeight(400),
                color = com.example.core.theme.FefuFitTheme.color.textColor.mainTextColor
            )
        },
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.back_arrow),
                    tint = com.example.core.theme.FefuFitTheme.color.elementsColor.elementColor,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = com.example.core.theme.FefuFitTheme.color.mainAppColors.appBackgroundColor
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
                .border(1.dp, com.example.core.theme.FefuFitTheme.color.elementsColor.elementColor, CircleShape)
                .size(32.dp)
                .background(com.example.core.theme.FefuFitTheme.color.elementsColor.elementColor, CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "1",
                color = com.example.core.theme.FefuFitTheme.color.elementsColor.onElementsColor,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 22.sp,
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .border(1.dp, com.example.core.theme.FefuFitTheme.color.elementsColor.elementColor, CircleShape)
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


