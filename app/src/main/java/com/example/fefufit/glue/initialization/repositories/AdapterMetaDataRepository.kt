package com.example.fefufit.glue.initialization.repositories

import com.example.data_store.DataStoreRepository
import com.example.data_store.entities.UserMetaData
import com.example.sing_in_impl.domain.models.FeatureUserMetaData
import com.example.sing_in_impl.domain.repositories.SingInFeatureMetaDataRepository
import javax.inject.Inject

class AdapterMetaDataSingInRepository @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
):SingInFeatureMetaDataRepository {
    override suspend fun saveUserMetaData(userMetaData: FeatureUserMetaData) {
        dataStoreRepository.setUserMetaData(
            userMetaData = UserMetaData(
                userMetaData.userToken,
                userMetaData.userQrToken
            )
        )
    }
}