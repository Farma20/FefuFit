package com.example.fefufit.glue.main_screen.repositories

import com.example.data_store.DataStoreRepository
import com.example.main_impl.domain.repositories.MainMetaDataRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AdapterMetaDataRepository @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
):MainMetaDataRepository {
    override suspend fun getUserTokenMetaData(): String {
        return dataStoreRepository.getUserMetaData().first().userMetaData.userToken!!
    }
}