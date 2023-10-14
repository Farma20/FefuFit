package com.example.feature_api.internal_data_api

import com.example.feature_api.internal_data_api.data.FeatureUserMetaData
import kotlinx.coroutines.flow.Flow

interface FeatureDataStoreApi {
    suspend fun getUserMetaData():FeatureUserMetaData

    suspend fun setUserMetaData(userMetaData: FeatureUserMetaData)
}