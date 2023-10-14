package com.example.initialization_impl.domain.repositories

import com.example.initialization_impl.domain.models.FeatureInitUserMetaData

interface SingInFeatureMetaDataRepository {
    suspend fun saveUserMetaData(userMetaData: FeatureInitUserMetaData)
}