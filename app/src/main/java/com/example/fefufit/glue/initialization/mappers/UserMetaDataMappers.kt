package com.example.fefufit.glue.initialization.mappers

import com.example.data_store.entities.UserMetaData
import com.example.initialization_impl.domain.models.FeatureUserMetaData

fun FeatureUserMetaData.toDataUserMetaData():UserMetaData{
    return UserMetaData(
        this.userToken,
        this.userQrToken
    )
}