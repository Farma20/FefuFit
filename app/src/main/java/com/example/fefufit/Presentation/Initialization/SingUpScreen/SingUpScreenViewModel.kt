package com.example.fefufit.Presentation.Initialization.SingUpScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.fefufit.Domain.Models.ValidationModels.SingUpFirstFormState
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateBirthdayUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateFirstNameUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateGenderUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateSecondNameUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateStatusUseCase
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Navigation.InputFieldsStates
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Validation.SingUpFirstFormEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SingUpScreenViewModel(
    private val validateSecondNameUseCase: ValidateSecondNameUseCase = ValidateSecondNameUseCase(),
    private val validateFirstNameUseCase: ValidateFirstNameUseCase = ValidateFirstNameUseCase(),
    private val validateGenderUseCase: ValidateGenderUseCase = ValidateGenderUseCase(),
    private val validateBirthdayUseCase: ValidateBirthdayUseCase = ValidateBirthdayUseCase(),
    private val validateStatusUseCase: ValidateStatusUseCase = ValidateStatusUseCase()
): ViewModel() {

    //pageStateVariables
    var inputFieldsNavController: NavController? = null
    var pageState by mutableStateOf<InputFieldsStates>(InputFieldsStates.FirstInputFields)
//    val pageState get() = _pageState
    fun getFieldsNavController(navController: NavController){
        inputFieldsNavController = navController
    }


    var inputDataState by mutableStateOf(SingUpFirstFormState())

    //a thread for sending notifications to the UI thread
    private val validationEventChannel = Channel<SingUpScreenViewModel.ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    //listener ui input events
    fun inputDataEvent(event: SingUpFirstFormEvent){
        when(event){
            is SingUpFirstFormEvent.SecondNameChanged ->{
                inputDataState = inputDataState.copy(secondName = event.secondName)
            }
            is SingUpFirstFormEvent.FirstNameChanged->{
                inputDataState = inputDataState.copy(firstName = event.firstName)
            }
            is SingUpFirstFormEvent.MiddleNameChanged->{
                inputDataState = inputDataState.copy(middleName = event.middleName)
            }
            is SingUpFirstFormEvent.GenderChanged ->{
                inputDataState = inputDataState.copy(gender = event.gender)
            }
            is SingUpFirstFormEvent.BirthdayChanged ->{
                inputDataState = inputDataState.copy(birthday = event.birthday)
            }
            is SingUpFirstFormEvent.StatusChanged ->{
                inputDataState = inputDataState.copy(status = event.status)
            }
            is SingUpFirstFormEvent.Submit ->{
                submitInputData()
            }
        }
    }

    //validate function
    private fun submitInputData() {
        val firstNameResult = validateFirstNameUseCase(inputDataState.firstName)
        val secondNameResult = validateSecondNameUseCase(inputDataState.secondName)
        val genderResult = validateGenderUseCase(inputDataState.gender)
        val birthdayResult = validateBirthdayUseCase(inputDataState.birthday)
        val statusResult = validateStatusUseCase(inputDataState.status)


        //find validate errors
        val hasError = listOf(
            firstNameResult,
            secondNameResult,
            genderResult,
            birthdayResult,
            statusResult
        ).any{ !it.success}

        if (hasError){
            inputDataState = inputDataState.copy(
                firstNameError = firstNameResult.errorMessage,
                secondNameError = secondNameResult.errorMessage,
                genderError = genderResult.errorMessage,
                birthdayError = birthdayResult.errorMessage,
                statusError = statusResult.errorMessage,
            )
            return
        }

        inputDataState = inputDataState.copy(
            firstNameError = null,
            secondNameError = null,
            genderError = null,
            birthdayError = null,
            statusError = null
        )

        viewModelScope.launch {
            validationEventChannel.send(SingUpScreenViewModel.ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent{
        object Success:ValidationEvent()
    }

//    fun setPageState(){
//        when (inputFieldsNavController!!.currentBackStackEntry!!.destination.route){
//            SingUpFieldsScreensRoute.SingUpFieldsFirst.route ->{
//                _pageState = InputFieldsStates.FirstInputFields
//            }
//            SingUpFieldsScreensRoute.SingUpFieldsFirst.route->{
//                _pageState = InputFieldsStates.SecondInputFields
//            }
//        }
//    }
}