package com.example.fefufit.Presentation.Initialization.SingUpScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefufit.Presentation.Initialization.SingInScreen.SingInFormEvent
import com.example.fefufit.Presentation.Initialization.SingInScreen.SingInScreenViewModel
import com.example.fefufit.Presentation.theme.BlackApp
import com.example.fefufit.Presentation.theme.BlueApp
import com.example.fefufit.Presentation.theme.RedErrorApp
import com.example.fefufit.Presentation.theme.SecondaryTextApp
import com.example.fefufit.Presentation.theme.WhiteApp
import com.example.fefufit.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpScreen(viewModel: SingUpScreenViewModel = viewModel()) {

    //painted system controllers
    val systemUiController = rememberSystemUiController()
    val barBackground = WhiteApp

    //painted system upp & bottom panels
    SideEffect {
        systemUiController.setStatusBarColor(color = barBackground, darkIcons = true)
        systemUiController.setNavigationBarColor(color = barBackground)
    }

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = context){
        viewModel.validationEvents.collect{event ->
            when(event){
                is SingUpScreenViewModel.ValidationEvent.Success ->{
                    snackBarHostState.showSnackbar(
                        message = "Success"
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
                    PersonalDataInputForm(viewModel)
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
fun PersonalDataInputForm(viewModel: SingUpScreenViewModel) {
    val inputDataState = viewModel.inputDataState

    //gender drop down menu variables
    var genderDropDown by remember { mutableStateOf(false) }
    val listGenders = listOf("Мужчина", "Женщина")
    var selectedGenderItem by remember { mutableStateOf("") }

    //status drop down menu variables
    var statusDropDown by remember { mutableStateOf(false) }
    val listStatus = listOf("Студент", "Гость", "Сотрудник")
    var selectedStatusItem by remember { mutableStateOf("") }

    //birthday datePicker variables
    val source = remember { MutableInteractionSource() }
    val pressedState=source.interactions.collectAsState(
        initial = PressInteraction.Cancel(PressInteraction.Press(Offset.Zero)))
    var pickedDate by remember { mutableStateOf(LocalDate.now()) }
    val dateDialogState = rememberMaterialDialogState()
    val dateNow by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(LocalDate.now())
        }
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }
    }


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
        value = inputDataState.secondName,
        isError = inputDataState.secondNameError != null,
        onValueChange = {
            viewModel.inputDataEvent(SingUpFormEvent.SecondNameChanged(it))
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

    if (inputDataState.secondNameError != null){
        Text(
            modifier = Modifier
                .fillMaxWidth(0.86f),
            text = inputDataState.secondNameError,
            fontSize =14.sp,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.End
        )
    }
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
        value = inputDataState.firstName,
        isError = inputDataState.firstNameError != null,
        onValueChange = {
            viewModel.inputDataEvent(SingUpFormEvent.FirstNameChanged(it))
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

    if (inputDataState.firstNameError != null){
        Text(
            modifier = Modifier
                .fillMaxWidth(0.86f),
            text = inputDataState.firstNameError,
            fontSize =14.sp,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.End
        )
    }
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
        value = inputDataState.middleName,
        onValueChange = {
            viewModel.inputDataEvent(SingUpFormEvent.MiddleNameChanged(it))
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
                Icon(
                    modifier = Modifier.rotate(
                        if (genderDropDown)
                            180f
                        else
                            0f
                    ),
                    painter = painterResource(id = R.drawable.bottom_arrow),
                    contentDescription = "bottomArrow",
                    tint = BlueApp
                )
            },
            shape = RoundedCornerShape(13.dp),
            isError = inputDataState.genderError != null,
            value = selectedGenderItem,
            onValueChange = {

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
                        viewModel.inputDataEvent(SingUpFormEvent.GenderChanged(selectedGenderItem))
                        genderDropDown = false
                    },
                )
            }
        }
    }

    if (inputDataState.genderError != null){
        Text(
            modifier = Modifier
                .fillMaxWidth(0.86f),
            text = inputDataState.genderError,
            fontSize =14.sp,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.End
        )
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
        value = if (formattedDate == dateNow)"" else formattedDate,
        isError = inputDataState.birthdayError != null,
        onValueChange = {
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BlueApp
        ),
        interactionSource = source,
        placeholder = {
            Text(
                text = "Не выбрано",
                fontSize = 18.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight(200),
                color = BlackApp,
            )
        },
        readOnly = true,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.calendar_icon),
                contentDescription = "calendar icon",
                tint = BlueApp
            )
        }
    )

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(
                text = "Ok",
                onClick = {
                    viewModel.inputDataEvent(SingUpFormEvent.BirthdayChanged(if (formattedDate == dateNow)"" else formattedDate))

                },
                textStyle = TextStyle(
                    color = BlueApp
                )
            )
            negativeButton(
                text = "Отмена",
                textStyle = TextStyle(
                    color = BlueApp
                )
            )
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Выберете дату рождения",
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = BlueApp,
                dateActiveBackgroundColor = BlueApp,
                dateActiveTextColor = WhiteApp,
            ),
        ) {
            pickedDate = it
        }
    }

    if (pressedState.value is PressInteraction.Release)
    {
        dateDialogState.show()
        source.tryEmit(PressInteraction.Cancel(PressInteraction.Press(Offset.Zero)))
    }

    if (inputDataState.birthdayError != null){
        Text(
            modifier = Modifier
                .fillMaxWidth(0.86f),
            text = inputDataState.birthdayError,
            fontSize =14.sp,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.End
        )
    }
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
                Icon(
                    modifier = Modifier.rotate(
                        if (statusDropDown)
                            180f
                        else
                            0f
                    ),
                    painter = painterResource(id = R.drawable.bottom_arrow),
                    contentDescription = "bottomArrow",
                    tint = BlueApp
                )
            },
            shape = RoundedCornerShape(13.dp),
            value = selectedStatusItem,
            onValueChange = {
            },
            isError = inputDataState.statusError != null,
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
                        viewModel.inputDataEvent(SingUpFormEvent.StatusChanged(selectedStatusItem))
                        statusDropDown = false
                    },
                )
            }
        }
    }

    if (inputDataState.statusError != null){
        Text(
            modifier = Modifier
                .fillMaxWidth(0.86f),
            text = inputDataState.statusError,
            fontSize =14.sp,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.End
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
    NextButton(viewModel)
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
private fun NextButton(viewModel: SingUpScreenViewModel){
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
            viewModel.inputDataEvent(SingUpFormEvent.Submit)
        }
    ) {
        Text(
            text = "Продолжить",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }
}