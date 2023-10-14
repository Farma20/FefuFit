package com.example.fefufit.glue.internal_data.mappers

import com.example.data_store.entities.AppInternalData
import com.example.data_store.entities.UserMetaData
import com.example.feature_api.internal_data_api.data.FeatureUserMetaData

fun AppInternalData.toFeatureUserMetaData(): FeatureUserMetaData{
    val data = this.userMetaData
    return FeatureUserMetaData(
        userQrToken = data.userQrToken,
        userToken = data.userToken,
        refreshToken = data.userRefreshToken,
        type = data.userType,
    )
}

fun FeatureUserMetaData.toUserMetaData(): UserMetaData{
    return UserMetaData(
        userToken = this.userToken,
        userQrToken = this.userQrToken,
        userRefreshToken = this.refreshToken,
        userType = this.type
    )
}