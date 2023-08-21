package com.example.fefufit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefufit.navigation.FeatureApiHolder
import com.example.main_api.MainPageApi
import com.example.sing_in_api.SingInApi
import com.example.sing_up_api.SingUpApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    override val singInScreen: SingInApi,
    override val singUpScreen: SingUpApi,
    override val mainScreen: MainPageApi
) :ViewModel(), FeatureApiHolder {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _isLoading.value = false
        }
    }
}