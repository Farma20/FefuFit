package com.example.fefufit.Presentation.Initialization.SingInScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fefufit.Domain.UseCases.Initial.SingInUseCase
import com.example.fefufit.Presentation.Initialization.Navigation.InitializationScreensRoute
import com.example.fefufit.Presentation.theme.BlueApp
import com.example.fefufit.Presentation.theme.SecondaryTextApp
import com.example.fefufit.Presentation.theme.WhiteApp
import com.example.fefufit.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingInScreen(navController: NavController) {

    val viewModel = hiltViewModel<SingInScreenViewModel>()

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

    LaunchedEffect(key1 = context){
        viewModel.validationEvents.collect{event ->
            when(event){
                is SingInScreenViewModel.ValidationEvent.Success ->{
                    snackBarHostState.showSnackbar(
                        message = "Success"
                    )
                }
                is SingInScreenViewModel.ValidationEvent.Error ->{
                    snackBarHostState.showSnackbar(
                        message = viewModel.errorData
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {SnackbarHost(snackBarHostState)}
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            color = WhiteApp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Image(
                    modifier = Modifier.size(95.dp, 103.dp),
                    painter = painterResource(id = R.drawable.fefufiticonblue),
                    contentDescription = "logo",
                )
                Spacer(modifier = Modifier.height(76.dp))
                InputForm(viewModel)
                Spacer(modifier = Modifier.height(24.dp))
                InputButton(viewModel)
                Spacer(modifier = Modifier.height(50.dp))
                SingUpButton(navController)
                Spacer(modifier = Modifier.height(57.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputForm(viewModel: SingInScreenViewModel){
    val inputDataState = viewModel.inputDataState

    Row(
        modifier = Modifier.fillMaxWidth(0.81f),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "E-mail или телефон",
            fontSize = 14.sp,
            color = SecondaryTextApp
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.85f),
        shape = RoundedCornerShape(13.dp),
        value = inputDataState.email,
        onValueChange = {
            viewModel.inputDataEvent(SingInFormEvent.EmailChanged(it))
        },
        isError = inputDataState.emailError != null,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BlueApp
        )
    )

    if (inputDataState.emailError != null){
        Text(
            modifier = Modifier
                .fillMaxWidth(0.81f),
            text = inputDataState.emailError,
            fontSize =14.sp,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.End
        )
    }
    
    Spacer(modifier = Modifier.height(14.dp))
    Row(
        modifier = Modifier.fillMaxWidth(0.81f),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Пароль",
            fontSize = 14.sp,
            color = SecondaryTextApp
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.85f),
        shape = RoundedCornerShape(13.dp),
        value = inputDataState.password,
        onValueChange = {
            viewModel.inputDataEvent(SingInFormEvent.PasswordChanged(it))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BlueApp
        )
    )
    if (inputDataState.passwordError != null){
        Text(
            modifier = Modifier
                .fillMaxWidth(0.81f),
            text = inputDataState.passwordError,
            fontSize =14.sp,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.End
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
    Row(
        modifier = Modifier.fillMaxWidth(0.85f),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(
            onClick = {}
        ) {
            Text(
                text = "Забыли пароль?",
                fontSize = 16.sp,
                color = BlueApp
            )
        }
    }
}

@Composable
private fun InputButton(viewModel: SingInScreenViewModel){
    Button(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(58.dp)
        ,
        shape = RoundedCornerShape(13.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = BlueApp
        ),
        onClick = {
            viewModel.inputDataEvent(SingInFormEvent.Submit)
        }
    ) {
        Text(
            text = "Войти",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
private fun SingUpButton(navController:NavController){
    Text(
        text = "Нет аккаунта?",
        color = SecondaryTextApp,
        fontSize = 18.sp,
    )
    TextButton(
        onClick = {navController.navigate(InitializationScreensRoute.SingUpScreen.route)}
    ){
        Text(
            text = "Зарегистрируйтесь!",
            color = BlueApp,
            fontSize = 18.sp
        )
    }
}

