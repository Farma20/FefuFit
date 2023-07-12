package com.example.fefufit.Presentation.Initialization.SingUpScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefufit.Presentation.theme.BlackApp
import com.example.fefufit.Presentation.theme.BlueApp
import com.example.fefufit.Presentation.theme.RedErrorApp
import com.example.fefufit.Presentation.theme.SecondaryTextApp
import com.example.fefufit.Presentation.theme.WhiteApp
import com.example.fefufit.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpScreen() {

    //painted system controllers
    val systemUiController = rememberSystemUiController()
    val barBackground = WhiteApp

    //painted system upp & bottom panels
    SideEffect {
        systemUiController.setStatusBarColor(color = barBackground, darkIcons = true)
        systemUiController.setNavigationBarColor(color = barBackground)
    }

    val scrollState = rememberScrollState()

    Scaffold(
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
                RegistrationPager(Modifier)
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Личные данные",
                    fontSize = 22.sp,
                    fontWeight = FontWeight(600),
                    color = BlackApp,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PersonalDataInputForm()
                }
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
private fun RegistrationPager(modifier: Modifier){
    Row(
        modifier = modifier
    ) {
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
                .background(Color.Transparent, CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "2",
                color = BlueApp,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 22.sp,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataInputForm() {

    //gender drop down menu variables
    var genderDropDown by remember { mutableStateOf(false) }
    val listGenders = listOf("Мужчина", "Женщина")
    var selectedGenderItem by remember { mutableStateOf("") }

    //status drop down menu variables
    var statusDropDown by remember { mutableStateOf(false) }
    val listStatus = listOf("Студент", "Гость", "Сотрудник")
    var selectedStatusItem by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(0.86f),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "Фамилия",
            fontSize = 14.sp,
            color = SecondaryTextApp
        )
        Text(
            text = " *",
            color = RedErrorApp
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.90f),
        shape = RoundedCornerShape(13.dp),
        value = "",
        onValueChange = {
//            viewModel.inputDataEvent(SingInFormEvent.EmailChanged(it))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BlueApp
        )
    )
    Spacer(modifier = Modifier.height(14.dp))
    Row(
        modifier = Modifier.fillMaxWidth(0.86f),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "Имя",
            fontSize = 14.sp,
            color = SecondaryTextApp
        )
        Text(
            text = " *",
            color = RedErrorApp,
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.90f),
        shape = RoundedCornerShape(13.dp),
        value = "",
        onValueChange = {
//            viewModel.inputDataEvent(SingInFormEvent.EmailChanged(it))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BlueApp
        )
    )
    Spacer(modifier = Modifier.height(14.dp))
    Row(
        modifier = Modifier.fillMaxWidth(0.86f),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "Отчество",
            fontSize = 14.sp,
            color = SecondaryTextApp
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.90f),
        shape = RoundedCornerShape(13.dp),
        value = "",
        onValueChange = {
//            viewModel.inputDataEvent(SingInFormEvent.EmailChanged(it))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BlueApp
        )
    )
    Spacer(modifier = Modifier.height(14.dp))
    Row(
        modifier = Modifier.fillMaxWidth(0.86f),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "Пол",
            fontSize = 14.sp,
            color = SecondaryTextApp
        )
        Text(
            text = " *",
            color = RedErrorApp,
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
    ExposedDropdownMenuBox(
        expanded = genderDropDown,
        onExpandedChange = {genderDropDown = !genderDropDown}
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .menuAnchor(),
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = genderDropDown
                )
            },
            shape = RoundedCornerShape(13.dp),
            value = selectedGenderItem,
            onValueChange = {
//            viewModel.inputDataEvent(SingInFormEvent.EmailChanged(it))
            },
            placeholder = {
                Text(
                    text = "Не выбрано",
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight(200),
                    color = BlackApp,
                )
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = BlueApp,
            )
        )
        DropdownMenu(
            modifier = Modifier
                .exposedDropdownSize()
                .background(WhiteApp),
            offset = DpOffset(0.dp, 6.dp),
            expanded = genderDropDown,
            onDismissRequest = { genderDropDown = false }
        ) {
            listGenders.forEach { selectedGender ->
                DropdownMenuItem(
                    modifier = Modifier
                        .background(WhiteApp),
                    text = { Text(text = selectedGender) },
                    onClick = {
                        selectedGenderItem = selectedGender
                        genderDropDown = false
                    },
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(14.dp))
    Row(
        modifier = Modifier.fillMaxWidth(0.86f),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "Дата рождения",
            fontSize = 14.sp,
            color = SecondaryTextApp
        )
        Text(
            text = " *",
            color = RedErrorApp,
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.90f),
        shape = RoundedCornerShape(13.dp),
        value = "",
        onValueChange = {
//            viewModel.inputDataEvent(SingInFormEvent.EmailChanged(it))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BlueApp
        )
    )
    Spacer(modifier = Modifier.height(14.dp))
    Row(
        modifier = Modifier.fillMaxWidth(0.86f),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "Статус",
            fontSize = 14.sp,
            color = SecondaryTextApp
        )
        Text(
            text = " *",
            color = RedErrorApp,
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
    ExposedDropdownMenuBox(
        expanded = statusDropDown,
        onExpandedChange = {statusDropDown = !statusDropDown}
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .menuAnchor(),
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = statusDropDown
                )
            },
            shape = RoundedCornerShape(13.dp),
            value = selectedStatusItem,
            onValueChange = {
//            viewModel.inputDataEvent(SingInFormEvent.EmailChanged(it))
            },
            placeholder = {
                Text(
                    text = "Не выбрано",
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight(200),
                    color = BlackApp,
                )
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = BlueApp,
            )
        )
        DropdownMenu(
            modifier = Modifier
                .exposedDropdownSize()
                .background(WhiteApp),
            offset = DpOffset(0.dp, 6.dp),
            expanded = statusDropDown,
            onDismissRequest = { statusDropDown = false }
        ) {
            listStatus.forEach { selectStatus ->
                DropdownMenuItem(
                    modifier = Modifier
                        .background(WhiteApp),
                    text = { Text(text = selectStatus) },
                    onClick = {
                        selectedStatusItem = selectStatus
                        statusDropDown = false
                    },
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
    NextButton()
    Spacer(modifier = Modifier.height(24.dp))

//    if (inputDataState.emailError != null) {
//        Text(
//            modifier = Modifier
//                .fillMaxWidth(0.81f),
//            text = inputDataState.emailError,
//            fontSize = 14.sp,
//            color = MaterialTheme.colorScheme.error,
//            textAlign = TextAlign.End
//        )
//    }
}

@Composable
private fun NextButton(){
    Button(
        modifier = Modifier
            .fillMaxWidth(0.90f)
            .height(58.dp)
        ,
        shape = RoundedCornerShape(13.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = BlueApp
        ),
        onClick = {
//            viewModel.inputDataEvent(SingInFormEvent.Submit)
        }
    ) {
        Text(
            text = "Продолжить",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }
}