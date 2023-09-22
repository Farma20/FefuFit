package com.example.timetable_impl.presentation.timetable_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.timetable_impl.domain.use_cases.AddEventUseCase
import com.example.timetable_impl.domain.use_cases.CancelEventUseCase
import com.example.timetable_impl.domain.use_cases.GetEventsUseCase
import com.example.timetable_impl.presentation.timetable_screen.models.EventsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class TimeTableScreenViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val addEventUseCase: AddEventUseCase,
    private val cancelEventUseCase: CancelEventUseCase
):ViewModel() {
    //data states variables
    private var _eventsState = MutableStateFlow(EventsState())
    val eventsState: StateFlow<EventsState> = _eventsState

    private val _errorStateChannel = Channel<ErrorResponse>()
    val errorState = _errorStateChannel.receiveAsFlow()

    init {
        getEvents()
    }

    fun addEvents(eventId:Int, isLoading:MutableState<Boolean>){
        addEventUseCase(eventId).onEach { result ->
            when(result){
                is Resource.Success ->{
                    isLoading.value = false
                    getEvents()
                }
                is Resource.Error ->{
                    isLoading.value = false
                    _errorStateChannel.send(ErrorResponse(result.data!!.details))
                }
                is Resource.Loading ->{
                    isLoading.value = true
                }
            }
        }
    }

    fun cancelEvents(eventId:Int, isLoading:MutableState<Boolean>){
        cancelEventUseCase(eventId).onEach { result ->
            when(result){
                is Resource.Success ->{
                    isLoading.value = false
                    getEvents()
                }
                is Resource.Error ->{
                    isLoading.value = false
                    _errorStateChannel.send(ErrorResponse(result.data!!.details))
                }
                is Resource.Loading ->{
                    isLoading.value = true
                }
            }
        }
    }

    private fun getEvents(){
        getEventsUseCase().onEach {result ->
            when(result){
                is Resource.Success ->{
                    _eventsState.value = EventsState(data = result.data)
                }
                is Resource.Error ->{
                    _eventsState.value = EventsState(error = result.message)
                }
                is Resource.Loading ->{
                    _eventsState.value = EventsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    data class ErrorResponse(val error:String)
}