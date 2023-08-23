package com.example.data_store.repository

import com.example.data_store.DataStoreRepository
import com.example.data_store.data_store_manager.DataStoreManager
import com.example.data_store.entities.AppInternalData
import com.example.data_store.entities.UserMetaData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class UserMetaDataStoreRepository @Inject constructor(
    private val dataStore: DataStoreManager
):DataStoreRepository {
    override fun getUserMetaData(): Flow<AppInternalData> {
        return dataStore.data
    }

    override suspend fun setUserMetaData(userMetaData: UserMetaData) {
        dataStore.setUserMetaData(userMetaData)
    }
}