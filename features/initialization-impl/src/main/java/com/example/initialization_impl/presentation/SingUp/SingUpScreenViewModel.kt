package com.example.initialization_impl.presentation.SingUp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.common.Resource
import com.example.initialization_impl.domain.models.FeatureSingInDataModel
import com.example.initialization_impl.domain.models.SingUpDataModel
import com.example.initialization_impl.domain.use_cases.SingInUseCase
import com.example.initialization_impl.domain.use_cases.SingUpUseCase
import com.example.initialization_impl.presentation.SingUp.data.SingUpFirstFormState
import com.example.initialization_impl.presentation.SingUp.data.SingUpSecondFormState
import com.example.initialization_impl.presentation.SingUp.navigation.InputFieldsStates
import com.example.initialization_impl.utils.validation.SingUpValidation.SingUpFirstFormEvent
import com.example.initialization_impl.utils.validation.SingUpValidation.SingUpSecondFormEvent
import com.example.initialization_impl.utils.validation.SingUpValidation.ValidateBirthdayUseCase
import com.example.initialization_impl.utils.validation.SingUpValidation.ValidateFirstNameUseCase
import com.example.initialization_impl.utils.validation.SingUpValidation.ValidateGenderUseCase
import com.example.initialization_impl.utils.validation.SingUpValidation.ValidatePhoneNumberUseCase
import com.example.initialization_impl.utils.validation.SingUpValidation.ValidateRepeatPasswordUseCase
import com.example.initialization_impl.utils.validation.SingUpValidation.ValidateSecondNameUseCase
import com.example.initialization_impl.utils.validation.SingUpValidation.ValidateSingUpEmailUseCase
import com.example.initialization_impl.utils.validation.SingUpValidation.ValidateSingUpPasswordUseCase
import com.example.initialization_impl.utils.validation.SingUpValidation.ValidateStatusUseCase
import com.example.initialization_impl.utils.validation.SingUpValidation.ValidateTermsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingUpScreenViewModel @Inject constructor(
    private val singInUseCase: SingInUseCase,
    private val singUpUseCase: SingUpUseCase,
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
    private val registrationEventChannel = Channel<RegistrationEvent>()
    val registrationFirstEvents = registrationEventChannel.receiveAsFlow()

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
            registrationEventChannel.send(RegistrationEvent.SuccessFirst)
        }
    }

    //_________________________________________________________


    //second registration page validation
    var inputSecondDataState by mutableStateOf(SingUpSecondFormState())


    fun inputSecondDataEvent(event: SingUpSecondFormEvent){
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
        inputDataState: SingUpFirstFormState,
        inputSecondDataState: SingUpSecondFormState
    ){
        println(inputDataState.birthday)
        singUpUseCase(
            SingUpDataModel(
            birthdate = inputDataState.birthday,
            email = inputSecondDataState.email,
            firstName = inputDataState.firstName,
            gender = inputDataState.gender,
            password = inputSecondDataState.password,
            phoneNumber = inputSecondDataState.phoneNumber,
            secondName = inputDataState.secondName,
            status = inputDataState.status,
            thirdName = inputDataState.middleName
        )
        ).onEach { result->
            when(result){
                is Resource.Success->{
                    registrationEventChannel.send(RegistrationEvent.SuccessSecond)
                    singInData(
                        FeatureSingInDataModel(
                            email = inputSecondDataState.email,
                            password = inputSecondDataState.password
                        )
                    )
                }
                is Resource.Error->{
                    errorData = result.message!!
                    registrationEventChannel.send(RegistrationEvent.SingUpError)
                }
                is Resource.Loading->{

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun singInData(featureSingInDataModel: FeatureSingInDataModel) {
        singInUseCase(featureSingInDataModel).onEach { result ->
            when(result){
                is Resource.Success->{
                    registrationEventChannel.send(RegistrationEvent.SingInSuccess)
                }
                is Resource.Error->{
                    errorData = result.message!!
                    registrationEventChannel.send(RegistrationEvent.SingInError)
                }
                is Resource.Loading->{

                }
            }
        }.launchIn(viewModelScope)
    }


    sealed class RegistrationEvent{
        object SuccessFirst: RegistrationEvent()
        object SuccessSecond: RegistrationEvent()
        object SingUpError: RegistrationEvent()
        object SingInSuccess:RegistrationEvent()
        object SingInError:RegistrationEvent()
    }
}