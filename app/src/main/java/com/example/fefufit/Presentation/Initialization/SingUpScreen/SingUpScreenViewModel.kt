package com.example.fefufit.Presentation.Initialization.SingUpScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.fefufit.Data.Remote.Models.InitialModels.SingInDataModel
import com.example.fefufit.Domain.Models.ValidationModels.SingUpFormState
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateBirthdayUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateFirstNameUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateGenderUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateSecondNameUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateStatusUseCase
import com.example.fefufit.Presentation.Initialization.SingInScreen.SingInFormEvent
import com.example.fefufit.Presentation.Initialization.SingInScreen.SingInScreenViewModel
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Navigation.SingUpFieldsScreensRoute
import com.example.fefufit.Utils.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
    var inputFieldsController: InputFieldsController? = null

    fun getFieldsNavController(navController: NavController){
        inputFieldsController = InputFieldsController(navController)
    }


    var inputDataState by mutableStateOf(SingUpFormState())
    var errorData by mutableStateOf("")

    //a thread for sending notifications to the UI thread
    private val validationEventChannel = Channel<SingUpScreenViewModel.ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    //listener ui input events
    fun inputDataEvent(event: SingUpFormEvent){
        when(event){
            is SingUpFormEvent.SecondNameChanged ->{
                inputDataState = inputDataState.copy(secondName = event.secondName)
            }
            is SingUpFormEvent.FirstNameChanged->{
                inputDataState = inputDataState.copy(firstName = event.firstName)
            }
            is SingUpFormEvent.MiddleNameChanged->{
                inputDataState = inputDataState.copy(middleName = event.middleName)
            }
            is SingUpFormEvent.GenderChanged ->{
                inputDataState = inputDataState.copy(gender = event.gender)
            }
            is SingUpFormEvent.BirthdayChanged ->{
                inputDataState = inputDataState.copy(birthday = event.birthday)
            }
            is SingUpFormEvent.StatusChanged ->{
                inputDataState = inputDataState.copy(status = event.status)
            }
            is SingUpFormEvent.Submit ->{
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


    inner class InputFieldsController(
        private var fieldsNavController: NavController
    ){
        fun goToFirstFields(){
            fieldsNavController.navigate(SingUpFieldsScreensRoute.SingUpFieldsFirst.route)
        }

        fun goToSecondFields(){
            fieldsNavController.navigate(SingUpFieldsScreensRoute.SingUpFieldsSecond.route)
        }
    }
}