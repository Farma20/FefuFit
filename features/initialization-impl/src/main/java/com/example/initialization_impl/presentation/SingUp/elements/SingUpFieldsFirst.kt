package com.example.initialization_impl.presentation.SingUp.elements

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.core.theme.FefuFitTheme
import com.example.initialization_impl.presentation.SingUp.SingUpScreenViewModel
import com.example.initialization_impl.presentation.SingUp.navigation.InputFieldsStates
import com.example.initialization_impl.R
import com.example.initialization_impl.utils.validation.SingUpValidation.SingUpFirstFormEvent
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
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
                .ofPattern("dd.MM.yyyy")
                .format(LocalDate.now())
        }
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd.MM.yyyy")
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
                color = FefuFitTheme.color.textColor.mainTextColor
            )
            Text(
                text = " *",
                color =  FefuFitTheme.color.mainAppColors.errorColor
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
                viewModel.inputDataEvent(SingUpFirstFormEvent.SecondNameChanged(it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                focusedBorderColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                textColor = FefuFitTheme.color.textColor.mainTextColor
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
                color = FefuFitTheme.color.textColor.mainTextColor
            )
            Text(
                text = " *",
                color = FefuFitTheme.color.mainAppColors.errorColor,
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
                viewModel.inputDataEvent(SingUpFirstFormEvent.FirstNameChanged(it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                focusedBorderColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                textColor = FefuFitTheme.color.textColor.mainTextColor
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
                color = FefuFitTheme.color.textColor.mainTextColor
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.90f),
            shape = RoundedCornerShape(13.dp),
            value = inputDataState.middleName,
            onValueChange = {
                viewModel.inputDataEvent(SingUpFirstFormEvent.MiddleNameChanged(it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                focusedBorderColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                textColor = FefuFitTheme.color.textColor.mainTextColor
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
                color = FefuFitTheme.color.textColor.mainTextColor
            )
            Text(
                text = " *",
                color = FefuFitTheme.color.mainAppColors.errorColor,
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
                        tint = FefuFitTheme.color.elementsColor.elementColor
                    )
                },
                shape = RoundedCornerShape(13.dp),
                isError = inputDataState.genderError != null,
                value = when (inputDataState.gender) {
                    "m" -> "Мужчина"
                    "f" -> "Женщина"
                    else -> ""
                },
                onValueChange = {

                },
                placeholder = {
                    Text(
                        text = "Не выбрано",
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(200),
                        color = FefuFitTheme.color.textColor.mainTextColor
                    )
                },
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                    focusedBorderColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                    textColor = FefuFitTheme.color.textColor.mainTextColor
                )
            )
            DropdownMenu(
                modifier = Modifier
                    .exposedDropdownSize()
                    .background(FefuFitTheme.color.mainAppColors.appCardColor),
                offset = DpOffset(0.dp, 6.dp),
                expanded = genderDropDown,
                onDismissRequest = { genderDropDown = false }
            ) {
                listGenders.forEach { selectedGender ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .background(FefuFitTheme.color.mainAppColors.appCardColor),
                        text = {
                            Text(
                                text = selectedGender,
                                color = FefuFitTheme.color.textColor.mainTextColor
                            )
                        },
                        onClick = {
                            selectedGenderItem = selectedGender
                            val shortGenderItem = if (selectedGenderItem == "Мужчина")"m" else "f"
                            viewModel.inputDataEvent(SingUpFirstFormEvent.GenderChanged(shortGenderItem))
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
                color = FefuFitTheme.color.textColor.mainTextColor
            )
            Text(
                text = " *",
                color = FefuFitTheme.color.mainAppColors.errorColor,
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.90f),
            shape = RoundedCornerShape(13.dp),
            value = inputDataState.birthday,
            isError = inputDataState.birthdayError != null,
            onValueChange = {
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                focusedBorderColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                textColor = FefuFitTheme.color.textColor.mainTextColor
            ),
            interactionSource = source,
            placeholder = {
                Text(
                    text = "Не выбрано",
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight(200),
                    color = FefuFitTheme.color.textColor.mainTextColor
                )
            },
            readOnly = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.calendar_icon),
                    contentDescription = "calendar icon",
                    tint = FefuFitTheme.color.elementsColor.elementColor
                )
            }
        )

        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(
                    text = "Ok",
                    onClick = {
                        viewModel.inputDataEvent(SingUpFirstFormEvent.BirthdayChanged(if (formattedDate == dateNow)"" else formattedDate))
                    },
                    textStyle = TextStyle(
                        color = FefuFitTheme.color.textColor.setTextColor
                    )
                )
                negativeButton(
                    text = "Отмена",
                    textStyle = TextStyle(
                        color = FefuFitTheme.color.textColor.setTextColor
                    )
                )
            }
        ) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Выберете дату рождения",
                colors = DatePickerDefaults.colors(
                    headerBackgroundColor = FefuFitTheme.color.elementsColor.setColor,
                    dateActiveBackgroundColor = FefuFitTheme.color.elementsColor.setColor,
                    dateActiveTextColor = FefuFitTheme.color.mainAppColors.appBottomNavColor,
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
                color = FefuFitTheme.color.textColor.mainTextColor
            )
            Text(
                text = " *",
                color = FefuFitTheme.color.mainAppColors.errorColor,
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
                        tint = FefuFitTheme.color.elementsColor.elementColor
                    )
                },
                shape = RoundedCornerShape(13.dp),
                value = when(inputDataState.status){
                            "student"->"Студент"
                            "guest"->"Гость"
                            "employee"->"Сотрудник"
                            else->""
                       },
                onValueChange = {
                },
                isError = inputDataState.statusError != null,
                placeholder = {
                    Text(
                        text = "Не выбрано",
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(200),
                        color = FefuFitTheme.color.textColor.mainTextColor,
                    )
                },
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                    focusedBorderColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                    textColor = FefuFitTheme.color.textColor.mainTextColor
                )
            )
            DropdownMenu(
                modifier = Modifier
                    .exposedDropdownSize()
                    .background(FefuFitTheme.color.mainAppColors.appCardColor),
                offset = DpOffset(0.dp, 6.dp),
                expanded = statusDropDown,
                onDismissRequest = { statusDropDown = false }
            ) {
                listStatus.forEach { selectStatus ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .background(FefuFitTheme.color.mainAppColors.appCardColor),
                        text = {
                                    Text(
                                        text = selectStatus,
                                        color = FefuFitTheme.color.textColor.mainTextColor
                                    )
                               },
                        onClick = {
                            selectedStatusItem = selectStatus
                            val shortStatus = when(selectedStatusItem){
                                "Студент"-> "student"
                                "Гость"-> "guest"
                                else -> "employee"
                            }
                            viewModel.inputDataEvent(SingUpFirstFormEvent.StatusChanged(shortStatus))
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
            containerColor = FefuFitTheme.color.mainAppColors.appBlueColor
        ),
        onClick = {
            viewModel.inputDataEvent(SingUpFirstFormEvent.Submit)
        }
    ) {
        Text(
            text = "Продолжить",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }
}