package com.example.fefufit.Presentation.Initialization.SingUpScreen.Elements

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Navigation.InputFieldsStates
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Navigation.SingUpFieldsScreensRoute
import com.example.fefufit.Presentation.Initialization.SingUpScreen.SingUpFormEvent
import com.example.fefufit.Presentation.Initialization.SingUpScreen.SingUpScreenViewModel
import com.example.fefufit.Presentation.theme.BlackApp
import com.example.fefufit.Presentation.theme.BlueApp
import com.example.fefufit.Presentation.theme.RedErrorApp
import com.example.fefufit.Presentation.theme.SecondaryTextApp
import com.example.fefufit.Presentation.theme.WhiteApp
import com.example.fefufit.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpFieldsFirst(
    viewModel: SingUpScreenViewModel,
) {
    viewModel.pageState = InputFieldsStates.FirstInputFields

    val inputDataState = viewModel.inputDataState
    val scrollState = rememberScrollState()

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


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
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

    }
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
//            viewModel.inputDataEvent(SingUpFormEvent.Submit)
            viewModel.inputFieldsNavController!!.navigate(SingUpFieldsScreensRoute.SingUpFieldsSecond.route)
        }
    ) {
        Text(
            text = "Продолжить",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }
}