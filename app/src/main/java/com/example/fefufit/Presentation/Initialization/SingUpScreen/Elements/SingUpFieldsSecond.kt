package com.example.fefufit.Presentation.Initialization.SingUpScreen.Elements

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Navigation.InputFieldsStates
import com.example.fefufit.Presentation.Initialization.SingUpScreen.SingUpScreenViewModel
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Validation.SingUpFirstFormEvent
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Validation.SingUpSecondFormEvent
import com.example.fefufit.Presentation.theme.BlueApp
import com.example.fefufit.Presentation.theme.RedErrorApp
import com.example.fefufit.Presentation.theme.SecondaryTextApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpFieldsSecond(viewModel: SingUpScreenViewModel) {
    viewModel.pageState = InputFieldsStates.SecondInputFields

    val inputDataState = viewModel.inputSecondDataState
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.86f),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = "Номер телефона",
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
            value = inputDataState.phoneNumber,
            isError = inputDataState.phoneNumberError != null,
            onValueChange = {
                viewModel.inputSecondDataEvent(SingUpSecondFormEvent.PhoneNumberChanged(it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = BlueApp
            )
        )

        if (inputDataState.phoneNumberError != null) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.86f),
                text = inputDataState.phoneNumberError,
                fontSize = 14.sp,
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
                text = "Почта",
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
            value = inputDataState.email,
            isError = inputDataState.emailError != null,
            onValueChange = {
                viewModel.inputSecondDataEvent(SingUpSecondFormEvent.EmailChanged(it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = BlueApp
            )
        )

        if (inputDataState.emailError != null) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.86f),
                text = inputDataState.emailError,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(0.86f),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Пароль",
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
            value = inputDataState.password,
            isError = inputDataState.passwordError != null,
            onValueChange = {
                viewModel.inputSecondDataEvent(SingUpSecondFormEvent.PasswordChanged(it))
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
        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(0.86f),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Повтор пароля",
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
            value = inputDataState.repeatPassword,
            isError = inputDataState.repeatPasswordError != null,
            onValueChange = {
                viewModel.inputSecondDataEvent(SingUpSecondFormEvent.RepeatPasswordChanged(it))
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
        if (inputDataState.repeatPasswordError != null){
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.81f),
                text = inputDataState.repeatPasswordError,
                fontSize =14.sp,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.93f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                colors = CheckboxDefaults.colors(
                    uncheckedColor = BlueApp,
                    checkedColor = BlueApp
                ),
                checked = inputDataState.terms,
                onCheckedChange = {
                    viewModel.inputSecondDataEvent(SingUpSecondFormEvent.TermsChanged(it))
                },
            )

            Row() {
                Text(
                    text = buildAnnotatedString {
                        append("Я согласен на обработку персональных данных")
                        withStyle(style = SpanStyle(color = RedErrorApp)){
                            append(" *")
                        }
                    },
                    fontSize = 13.sp,
                    fontWeight = FontWeight(300),
                    color = Color(0xFF383838),
                    lineHeight = 16.sp
                )
            }
        }
        if(inputDataState.termsError != null){
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.81f),
                text = inputDataState.termsError,
                fontSize =14.sp,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        RegistrationButton(viewModel = viewModel)
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun RegistrationButton(viewModel: SingUpScreenViewModel){
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
            viewModel.inputSecondDataEvent(SingUpSecondFormEvent.Submit)
        }
    ) {
        Text(
            text = "Зарегистрироваться",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }
}