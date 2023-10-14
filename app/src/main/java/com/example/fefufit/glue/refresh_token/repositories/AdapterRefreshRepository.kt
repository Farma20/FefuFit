package com.example.fefufit.glue.refresh_token.repositories

import com.example.feature_api.refresh_api.FeatureRefreshTokenApi
import com.example.feature_api.refresh_api.dto.FeatureRefreshMetaDTO
import com.example.feature_api.refresh_api.dto.FeatureRefreshTokenDTO
import com.example.fefufit.glue.refresh_token.mappers.toFeatureRefreshMetaDTO
import com.example.remote.RefreshDataRepository
import com.example.remote.models.initial_data_models.RefreshTokenDTO
import javax.inject.Inject

class AdapterRefreshRepository @Inject constructor(
    private val refreshRepo: RefreshDataRepository
):FeatureRefreshTokenApi{
    override suspend fun refreshToken(refreshToken: FeatureRefreshTokenDTO): FeatureRefreshMetaDTO {
        val newUserMetaData = refreshRepo.refreshToken(RefreshTokenDTO(refreshToken.refreshToken))
        return newUserMetaData.toFeatureRefreshMetaDTO()
    }
}