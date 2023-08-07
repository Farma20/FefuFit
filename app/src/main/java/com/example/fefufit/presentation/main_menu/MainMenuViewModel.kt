package com.example.fefufit.presentation.main_menu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefufit.data.remote.models.user_data_models.UserShortDataModel
import com.example.fefufit.domain.use_cases.main.user_use_cases.UserActiveServiceUseCase
import com.example.fefufit.domain.use_cases.main.user_use_cases.UserNearBookingUseCase
import com.example.fefufit.domain.use_cases.main.user_use_cases.UserShortDataUseCase
import com.example.fefufit.presentation.main_menu.models.NearBookingDataState
import com.example.fefufit.presentation.main_menu.models.UserDataState
import com.example.fefufit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val userActiveServiceUseCase:UserActiveServiceUseCase,
    private val userNearBookingUseCase: UserNearBookingUseCase,
    private val userShortDataUseCase: UserShortDataUseCase,
):ViewModel() {

    //data states variables
    private var _userDataState = mutableStateOf(UserDataState())
    val userDataState:State<UserDataState> = _userDataState

    private var _nearBookingState = mutableStateOf(NearBookingDataState())
    val nearBookingState:State<NearBookingDataState> = _nearBookingState

    init {
        getUserData()
        getNearBooking()
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


}