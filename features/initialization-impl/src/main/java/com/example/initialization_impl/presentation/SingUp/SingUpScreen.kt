package com.example.initialization_impl.presentation.SingUp

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.core.theme.FefuFitTheme
import com.example.initialization_impl.presentation.SingUp.navigation.InputFieldsStates
import com.example.initialization_impl.presentation.SingUp.navigation.SingUpFieldsScreens
import com.example.initialization_impl.presentation.SingUp.navigation.SingUpFieldsScreensRoute
import com.example.sing_in_impl.R


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpScreen(
    modifier: Modifier,
    onNavigateToMain: () -> Unit,
    onNavigateToSingIn: () -> Unit
) {

    val viewModel = hiltViewModel<SingUpScreenViewModel>()

    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    //page variables
    var labelText by remember { mutableStateOf("Личные данные") }
    var circlePagerColor by remember { mutableStateOf(Color.Transparent) }
    val background = FefuFitTheme.color.elementsColor.onElementsColor
    var circlePagerTextColor by remember {
        mutableStateOf(background)
    }

    when(viewModel.pageState){
        InputFieldsStates.FirstInputFields -> {
            labelText = "Личные данные"
            circlePagerColor = Color.Transparent
            circlePagerTextColor = FefuFitTheme.color.textColor.mainTextColor
        }
        InputFieldsStates.SecondInputFields -> {
            labelText = "Учетные данные"
            circlePagerColor = FefuFitTheme.color.elementsColor.elementColor
            circlePagerTextColor = FefuFitTheme.color.elementsColor.onElementsColor
        }
    }

    LaunchedEffect(key1 = context){
        viewModel.registrationFirstEvents.collect{event ->
            when(event){
                is SingUpScreenViewModel.RegistrationEvent.SuccessFirst ->{
                    viewModel.inputFieldsNavController!!.navigate(SingUpFieldsScreensRoute.SingUpFieldsSecond.route)
                }
                is SingUpScreenViewModel.RegistrationEvent.SuccessSecond ->{
                    snackBarHostState.showSnackbar(
                        message = "Success"
                    )
                }
                is SingUpScreenViewModel.RegistrationEvent.SingUpError ->{
                    snackBarHostState.showSnackbar(
                        message = viewModel.errorData
                    )
                }
                is SingUpScreenViewModel.RegistrationEvent.SingInError -> {
                    snackBarHostState.showSnackbar(
                        message = viewModel.errorData
                    )
                }
                is SingUpScreenViewModel.RegistrationEvent.SingInSuccess -> {
                    onNavigateToMain()
                }
            }
        }


    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            UppBar(
                modifier = Modifier,
                onBackClick = onNavigateToSingIn
            )
        }
    ) {scaffoldPadding->
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = FefuFitTheme.color.mainAppColors.appBackgroundColor
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
                    color = FefuFitTheme.color.textColor.mainTextColor,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(24.dp))

                SingUpFieldsScreens(
                    viewModel,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UppBar(
    modifier: Modifier,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Регистрация",
                fontSize = 22.sp,
                fontWeight = FontWeight(400),
                color = FefuFitTheme.color.textColor.mainTextColor
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.back_arrow),
                    tint = FefuFitTheme.color.elementsColor.elementColor,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = FefuFitTheme.color.mainAppColors.appBackgroundColor
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
                .border(1.dp, FefuFitTheme.color.elementsColor.elementColor, CircleShape)
                .size(32.dp)
                .background(FefuFitTheme.color.elementsColor.elementColor, CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "1",
                color = FefuFitTheme.color.elementsColor.onElementsColor,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 22.sp,
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .border(1.dp, FefuFitTheme.color.elementsColor.elementColor, CircleShape)
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


