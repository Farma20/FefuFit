package com.example.initialization_impl.presentation.SingIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.initialization_impl.domain.models.FeatureSingInDataModel
import com.example.initialization_impl.domain.use_cases.SingInUseCase
import com.example.initialization_impl.utils.validation.SingInValidation.SingInFormState
import com.example.initialization_impl.utils.validation.SingInValidation.ValidateEmailUseCase
import com.example.initialization_impl.utils.validation.SingInValidation.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SingInScreenViewModel @Inject constructor(
    private val singInUseCase: SingInUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
):ViewModel() {
    var inputDataState by mutableStateOf(SingInFormState())
    var errorData by mutableStateOf("")


    //a thread for sending notifications to the UI thread
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    //listener ui input events
    fun inputDataEvent(event: SingInFormEvent){
        when(event){
            is SingInFormEvent.EmailChanged ->{
                inputDataState = inputDataState.copy(email = event.email)
            }
            is SingInFormEvent.PasswordChanged ->{
                inputDataState = inputDataState.copy(password = event.password)
            }
            is SingInFormEvent.Submit ->{
                submitInputData()
            }
        }
    }

    //validate function
    private fun submitInputData() {
        val emailResult = validateEmailUseCase(inputDataState.email)
        val passwordResult = validatePasswordUseCase(inputDataState.password)

        //find validate errors
        val hasError = listOf(
            emailResult,
            passwordResult,
        ).any{ !it.success}

        if (hasError){
            inputDataState = inputDataState.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }

        inputDataState = inputDataState.copy(
            emailError = null,
            passwordError = null
        )

        singInData(inputDataState.email, inputDataState.password)
    }

    private fun singInData(email:String, password:String){
        singInUseCase(FeatureSingInDataModel(email, password)).onEach { result->
            when(result){
                is Resource.Success->{
                    validationEventChannel.send(ValidationEvent.Success)
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
        object Success: ValidationEvent()
        object Error: ValidationEvent()
    }
}