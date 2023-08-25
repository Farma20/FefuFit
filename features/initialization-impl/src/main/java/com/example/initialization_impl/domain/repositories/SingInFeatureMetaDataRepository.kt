package com.example.initialization_impl.domain.repositories

import com.example.initialization_impl.domain.models.FeatureUserMetaData

interface SingInFeatureMetaDataRepository {
    suspend fun saveUserMetaData(userMetaData: FeatureUserMetaData)
}