package com.example.fefufit.Presentation.Initialization.SingUpScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.fefufit.Domain.Models.ValidationModels.SingUpFirstFormState
import com.example.fefufit.Domain.Models.ValidationModels.SingUpSecondFormState
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateBirthdayUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateFirstNameUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateGenderUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidatePhoneNumberUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateRepeatPasswordUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateSecondNameUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateSingUpEmailUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateSingUpPasswordUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateStatusUseCase
import com.example.fefufit.Domain.UseCases.Initial.Validation.SingUpValidation.ValidateTermsUseCase
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Navigation.InputFieldsStates
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Validation.SingUpFirstFormEvent
import com.example.fefufit.Presentation.Initialization.SingUpScreen.Validation.SingUpSecondFormEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SingUpScreenViewModel(
    private val validateSecondNameUseCase: ValidateSecondNameUseCase = ValidateSecondNameUseCase(),
    private val validateFirstNameUseCase: ValidateFirstNameUseCase = ValidateFirstNameUseCase(),
    private val validateGenderUseCase: ValidateGenderUseCase = ValidateGenderUseCase(),
    private val validateBirthdayUseCase: ValidateBirthdayUseCase = ValidateBirthdayUseCase(),
    private val validateStatusUseCase: ValidateStatusUseCase = ValidateStatusUseCase(),
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase = ValidatePhoneNumberUseCase(),
    private val validateEmailUseCase: ValidateSingUpEmailUseCase = ValidateSingUpEmailUseCase(),
    private val validatePasswordUseCase: ValidateSingUpPasswordUseCase = ValidateSingUpPasswordUseCase(),
    private val validateRepeatPasswordUseCase: ValidateRepeatPasswordUseCase = ValidateRepeatPasswordUseCase(),
    private val validateTermsUseCase: ValidateTermsUseCase = ValidateTermsUseCase(),
): ViewModel() {
    //pageStateVariables
    var inputFieldsNavController: NavController? = null
    var pageState by mutableStateOf<InputFieldsStates>(InputFieldsStates.FirstInputFields)

    fun getFieldsNavController(navController: NavController){
        inputFieldsNavController = navController
    }

    //first registration page validation
    var inputDataState by mutableStateOf(SingUpFirstFormState())

    //a thread for sending notifications to the UI thread
    private val validationEventChannel = Channel<ValidationFirstEvent>()
    val validationFirstEvents = validationEventChannel.receiveAsFlow()

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
            validationEventChannel.send(SingUpScreenViewModel.ValidationFirstEvent.Success)
        }
    }

    sealed class ValidationFirstEvent{
        object Success:ValidationFirstEvent()
    }
    //_________________________________________________________


    //second registration page validation
    var inputSecondDataState by mutableStateOf(SingUpSecondFormState())

    private val validationSecondEventChannel = Channel<ValidationSecondEvent>()
    val validationSecondEvents = validationSecondEventChannel.receiveAsFlow()

    fun inputSecondDataEvent(event:SingUpSecondFormEvent){
        when(event){
            is SingUpSecondFormEvent.PhoneNumberChanged ->{
                inputSecondDataState = inputSecondDataState.copy(phoneNumber = event.phoneNumber)
            }
            is SingUpSecondFormEvent.EmailChanged->{
                inputSecondDataState = inputSecondDataState.copy(email = event.email)
            }
            is SingUpSecondFormEvent.PasswordChanged->{
                inputSecondDataState = inputSecondDataState.copy(password = event.password)
            }
            is SingUpSecondFormEvent.RepeatPasswordChanged ->{
                inputSecondDataState = inputSecondDataState.copy(repeatPassword = event.repeatPassword)
            }
            is SingUpSecondFormEvent.TermsChanged ->{
                inputSecondDataState = inputSecondDataState.copy(terms = event.terms)
            }
            is SingUpSecondFormEvent.Submit ->{
                submitSecondInputData()
            }
        }
    }

    private fun submitSecondInputData() {
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
            validationEventChannel.send(SingUpScreenViewModel.ValidationFirstEvent.Success)
        }
    }

    sealed class ValidationSecondEvent{
        object Success:ValidationSecondEvent()
    }
    //_________________________________________________________
}