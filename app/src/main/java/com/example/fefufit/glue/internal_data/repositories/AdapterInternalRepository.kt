package com.example.fefufit.glue.internal_data.repositories

import android.util.Log
import com.example.data_store.DataStoreRepository
import com.example.feature_api.internal_data_api.FeatureDataStoreApi
import com.example.feature_api.internal_data_api.data.FeatureUserMetaData
import com.example.fefufit.glue.internal_data.mappers.toFeatureUserMetaData
import com.example.fefufit.glue.internal_data.mappers.toUserMetaData
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AdapterInternalRepository @Inject constructor(
    private val dataStore: DataStoreRepository
):FeatureDataStoreApi {
    override suspend fun getUserMetaData(): FeatureUserMetaData {
        val userMetaData = dataStore.getUserMetaData().first()
        return userMetaData.toFeatureUserMetaData()
    }

    override suspend fun setUserMetaData(userMetaData: FeatureUserMetaData) {
        dataStore.setUserMetaData(userMetaData.toUserMetaData())
    }

}