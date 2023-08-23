package com.example.sing_in_impl.domain.repositories

import com.example.sing_in_impl.domain.models.FeatureUserMetaData

interface SingInFeatureMetaDataRepository {
    suspend fun saveUserMetaData(userMetaData: FeatureUserMetaData)
}