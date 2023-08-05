package com.example.fefufit.presentation.main_menu

import androidx.lifecycle.ViewModel
import com.example.fefufit.domain.use_cases.main.user_use_cases.UserActiveServiceUseCase
import com.example.fefufit.domain.use_cases.main.user_use_cases.UserNearBookingUseCase
import com.example.fefufit.domain.use_cases.main.user_use_cases.UserShortDataUseCase
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    private val userActiveServiceUseCase:UserActiveServiceUseCase,
    private val userNearBookingUseCase: UserNearBookingUseCase,
    private val userShortDataUseCase: UserShortDataUseCase,
):ViewModel() {

}