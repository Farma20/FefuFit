package com.example.fefufit.glue.initialization.repositories

import com.example.data_store.DataStoreRepository
import com.example.fefufit.glue.initialization.mappers.toDataUserMetaData
import com.example.initialization_impl.domain.models.FeatureUserMetaData
import com.example.initialization_impl.domain.repositories.SingInFeatureMetaDataRepository
import javax.inject.Inject

class AdapterMetaDataSingInRepository @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
):SingInFeatureMetaDataRepository {
    override suspend fun saveUserMetaData(userMetaData: FeatureUserMetaData) {
        dataStoreRepository.setUserMetaData(
            userMetaData = userMetaData.toDataUserMetaData()
        )
    }
}