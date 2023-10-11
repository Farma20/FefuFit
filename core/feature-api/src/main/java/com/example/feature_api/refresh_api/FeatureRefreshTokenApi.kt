package com.example.feature_api.refresh_api

import com.example.feature_api.refresh_api.dto.FeatureRefreshMetaDTO
import com.example.feature_api.refresh_api.dto.FeatureRefreshTokenDTO

interface FeatureRefreshTokenApi {
    suspend fun refreshToken(refreshToken: FeatureRefreshTokenDTO): FeatureRefreshMetaDTO
}