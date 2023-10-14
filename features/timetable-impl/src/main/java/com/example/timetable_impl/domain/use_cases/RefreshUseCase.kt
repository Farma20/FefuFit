package com.example.timetable_impl.domain.use_cases

import android.util.Log
import com.example.feature_api.internal_data_api.FeatureDataStoreApi
import com.example.feature_api.internal_data_api.data.FeatureUserMetaData
import com.example.feature_api.refresh_api.FeatureRefreshTokenApi
import com.example.feature_api.refresh_api.dto.FeatureRefreshTokenDTO
import javax.inject.Inject

class RefreshUseCase @Inject constructor(
    private val refreshTokenRepository: FeatureRefreshTokenApi,
    private val metaDataRepository: FeatureDataStoreApi
) {
    suspend operator fun invoke(){
        try {
            val refreshToken = metaDataRepository.getUserMetaData().refreshToken
            val newUserMetaData = refreshTokenRepository
                .refreshToken(FeatureRefreshTokenDTO(refreshToken!!))
                .data

            metaDataRepository.setUserMetaData(
                FeatureUserMetaData(
                    userToken = newUserMetaData.token,
                    userQrToken = newUserMetaData.qrToken,
                    refreshToken = newUserMetaData.refreshToken,
                    type = newUserMetaData.type,
                )
            )
        }catch (e:Exception){
            Log.d("RefreshUseCase", e.stackTrace.toString())
        }
    }
}
