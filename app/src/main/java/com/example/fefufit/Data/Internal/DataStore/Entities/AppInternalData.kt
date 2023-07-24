package com.example.fefufit.Data.Internal.DataStore.Entities

import kotlinx.serialization.Serializable

@Serializable
data class AppInternalData(
    val userMetaData: UserMetaData = UserMetaData()
)

@Serializable
data class UserMetaData(
    val userToken:String? = null,
    val userQrToken: String? = null
)