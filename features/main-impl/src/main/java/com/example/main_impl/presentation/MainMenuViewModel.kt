package com.example.main_impl.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.main_impl.domain.use_cases.GenerateQrCodeUseCase
import com.example.main_impl.domain.use_cases.UserActiveServiceUseCase
import com.example.main_impl.domain.use_cases.UserNearBookingUseCase
import com.example.main_impl.domain.use_cases.UserShortDataUseCase
import com.example.main_impl.presentation.models.ActiveServicesState
import com.example.main_impl.presentation.models.NearBookingDataState
import com.example.main_impl.presentation.models.QrCodeState
import com.example.main_impl.presentation.models.UserDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val userActiveServiceUseCase: UserActiveServiceUseCase,
    private val userNearBookingUseCase: UserNearBookingUseCase,
    private val userShortDataUseCase: UserShortDataUseCase,
    private val generateQrCodeUseCase: GenerateQrCodeUseCase,
):ViewModel() {

    //data states variables
    private var _userDataState = mutableStateOf(UserDataState())
    val userDataState:State<UserDataState> = _userDataState

    private var _nearBookingState = mutableStateOf(NearBookingDataState())
    val nearBookingState:State<NearBookingDataState> = _nearBookingState

    private var _activeServicesState = mutableStateOf(ActiveServicesState())
    val activeServicesState:State<ActiveServicesState> = _activeServicesState

    private var _qrCodeState = mutableStateOf(QrCodeState())
    val qrCodeState:State<QrCodeState> = _qrCodeState

    init {
        getUserData()
        getNearBooking()
        getActiveServices()
        getQrCode()
    }

    private fun getActiveServices() {
        userActiveServiceUseCase().onEach { result ->
            when(result){
                is Resource.Success ->{
                    _activeServicesState.value = ActiveServicesState(data = result.data)
                }
                is Resource.Error ->{
                    _activeServicesState.value = ActiveServicesState(error = result.message)
                }
                is Resource.Loading ->{
                    _activeServicesState.value = ActiveServicesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getNearBooking() {
        userNearBookingUseCase().onEach { result ->
            when(result){
                is Resource.Success ->{
                    _nearBookingState.value = NearBookingDataState(data = result.data)
                }
                is Resource.Error ->{
                    _nearBookingState.value = NearBookingDataState(error = result.message)
                }
                is Resource.Loading ->{
                    _nearBookingState.value = NearBookingDataState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserData(){
        userShortDataUseCase().onEach { result->
            when(result){
                is Resource.Success ->{
                    _userDataState.value = UserDataState(data = result.data)
                }
                is Resource.Error ->{
                    _userDataState.value = UserDataState(error = result.message)
                }
                is Resource.Loading ->{
                    _userDataState.value = UserDataState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getQrCode(){
        generateQrCodeUseCase().onEach {result->
            when(result){
                is Resource.Error -> {
                    _qrCodeState.value = QrCodeState(error = result.message)
                }
                is Resource.Loading -> {
                    _qrCodeState.value = QrCodeState(isLoading = true)
                }
                is Resource.Success -> {
                    _qrCodeState.value = QrCodeState(data = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}