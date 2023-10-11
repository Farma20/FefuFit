package com.example.remote

import com.example.remote.models.initial_data_models.DataSingInResponse
import com.example.remote.models.initial_data_models.RefreshTokenDTO

interface RefreshDataRepository{
    suspend fun refreshToken(refreshToken: RefreshTokenDTO): DataSingInResponse
}