package com.example.data_store.di

import com.example.data_store.DataStoreRepository
import com.example.data_store.repository.UserMetaDataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindDataStoreRepository(
        dataStoreRepository: UserMetaDataStoreRepository
    ):DataStoreRepository
}