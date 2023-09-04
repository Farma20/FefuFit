package com.example.main_impl.presentation.models

import android.graphics.Bitmap
import com.example.main_impl.domain.models.UserServicesDataModel

data class QrCodeState(
    val isLoading:Boolean = false,
    val data: Bitmap? = null,
    val error: String? = null
)