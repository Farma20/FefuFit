package com.example.fefufit.presentation.initialization.sing_up_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.fefufit.data.remote.models.initial_data_models.SingUpDataModel
import com.example.fefufit.domain.models.validation_models.SingUpFirstFormState
import com.example.fefufit.domain.models.validation_models.SingUpSecondFormState
import com.example.fefufit.domain.use_cases.initial.SingUpUseCase
import com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation.ValidateBirthdayUseCase
import com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation.ValidateFirstNameUseCase
import com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation.ValidateGenderUseCase
import com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation.ValidatePhoneNumberUseCase
import com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation.ValidateRepeatPasswordUseCase
import com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation.ValidateSecondNameUseCase
import com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation.ValidateSingUpEmailUseCase
import com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation.ValidateSingUpPasswordUseCase
import com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation.ValidateStatusUseCase
import com.example.fefufit.domain.use_cases.initial.validation.sing_up_validation.ValidateTermsUseCase
import com.example.fefufit.presentation.initialization.sing_up_screen.navigation.InputFieldsStates
import com.example.fefufit.presentation.initialization.sing_up_screen.validation.SingUpFirstFormEvent
import com.example.fefufit.presentation.initialization.sing_up_screen.validation.SingUpSecondFormEvent
import com.example.fefufit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingUpScreenViewModel @Inject constructor(
    private var singUpUseCase: SingUpUseCase,
    private val validateSecondNameUseCase: ValidateSecondNameUseCase,
    private val validateFirstNameUseCase: ValidateFirstNameUseCase,
    private val validateGenderUseCase: ValidateGenderUseCase,
    private val validateBirthdayUseCase: ValidateBirthdayUseCase,
    private val validateStatusUseCase: ValidateStatusUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val validateEmailUseCase: ValidateSingUpEmailUseCase,
    private val validatePasswordUseCase: ValidateSingUpPasswordUseCase,
    private val validateRepeatPasswordUseCase: ValidateRepeatPasswordUseCase,
    private val validateTermsUseCase: ValidateTermsUseCase,
): ViewModel() {
    //pageStateVariables
    var inputFieldsNavController: NavController? = null
    var pageState by mutableStateOf<InputFieldsStates>(InputFieldsStates.FirstInputFields)

    fun getFieldsNavController(navController: NavController){
        inputFieldsNavController = navController
    }

    //first registration page validation
    var inputDataState by mutableStateOf(SingUpFirstFormState())
    var errorData by mutableStateOf("")

    //a thread for sending notifications to the UI thread
    private val validationEventChannel = Channel<ValidationEvent>()
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
            validationEventChannel.send(ValidationEvent.SuccessFirst)
        }
    }

    //_________________________________________________________


    //second registration page validation
    var inputSecondDataState by mutableStateOf(SingUpSecondFormState())


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
        val phoneNumberResult = validatePhoneNumberUseCase(inputSecondDataState.phoneNumber)
        val emailResult = validateEmailUseCase(inputSecondDataState.email)
        val passwordResult = validatePasswordUseCase(inputSecondDataState.password)
        val repeatPassword = validateRepeatPasswordUseCase(
            inputSecondDataState.password,
            inputSecondDataState.repeatPassword
        )
        val termsResult = validateTermsUseCase(inputSecondDataState.terms)


        //find validate errors
        val hasError = listOf(
            phoneNumberResult,
            emailResult,
            passwordResult,
            repeatPassword,
            termsResult
        ).any{ !it.success}

        if (hasError){
            inputSecondDataState = inputSecondDataState.copy(
                phoneNumberError = phoneNumberResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatPasswordError = repeatPassword.errorMessage,
                termsError = termsResult.errorMessage,
            )
            return
        }

        println("_______yes__________")

        inputSecondDataState = inputSecondDataState.copy(
            phoneNumberError = null,
            emailError = null,
            passwordError = null,
            repeatPasswordError = null,
            termsError = null
        )

        viewModelScope.launch {
            singUpData(inputDataState, inputSecondDataState)
        }
    }
    //_________________________________________________________

    private fun singUpData(
        inputDataState:SingUpFirstFormState,
        inputSecondDataState: SingUpSecondFormState
    ){
        println(inputDataState.birthday)
        singUpUseCase(SingUpDataModel(
            birthdate = inputDataState.birthday,
            email = inputSecondDataState.email,
            firstName = inputDataState.firstName,
            gender = inputDataState.gender,
            password = inputSecondDataState.password,
            phoneNumber = inputSecondDataState.phoneNumber,
            secondName = inputDataState.secondName,
            status = inputDataState.status,
            thirdName = inputDataState.middleName
        )).onEach { result->
            when(result){
                is Resource.Success->{
                    validationEventChannel.send(ValidationEvent.SuccessSecond)
                }
                is Resource.Error->{
                    errorData = result.message!!
                    validationEventChannel.send(ValidationEvent.Error)
                }
                is Resource.Loading->{

                }
            }

        }.launchIn(viewModelScope)
    }

    sealed class ValidationEvent{
        object SuccessFirst:ValidationEvent()
        object SuccessSecond:ValidationEvent()
        object Error:ValidationEvent()
    }
}