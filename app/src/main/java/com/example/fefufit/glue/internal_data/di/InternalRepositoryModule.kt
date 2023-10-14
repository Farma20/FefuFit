package com.example.fefufit.glue.internal_data.di

import com.example.feature_api.internal_data_api.FeatureDataStoreApi
import com.example.fefufit.glue.internal_data.repositories.AdapterInternalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InternalRepositoryModule {
    @Binds
    fun bindInternalRepository(
        repository:AdapterInternalRepository
    ): FeatureDataStoreApi
}