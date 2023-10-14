package com.example.fefufit.glue.refresh_token.mappers

import com.example.feature_api.refresh_api.dto.FeatureDataUserMeta
import com.example.feature_api.refresh_api.dto.FeatureRefreshMetaDTO
import com.example.remote.models.initial_data_models.DataSingInResponse

fun DataSingInResponse.toFeatureRefreshMetaDTO():FeatureRefreshMetaDTO{
    val data = this.data
    return FeatureRefreshMetaDTO(
        data = FeatureDataUserMeta(
            qrToken = data.qrToken,
            refreshToken = data.refreshToken,
            token = data.token,
            type = data.type
        ),
        status = this.status
    )
}