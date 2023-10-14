package com.example.fefufit.glue.initialization.mappers

import com.example.data_store.entities.UserMetaData
import com.example.initialization_impl.domain.models.FeatureInitUserMetaData

fun FeatureInitUserMetaData.toDataUserMetaData():UserMetaData{
    return UserMetaData(
        this.userToken,
        this.userQrToken,
        this.refreshToken,
        this.type
    )
}