package com.example.fefufit.glue.initialization.di

import com.example.fefufit.glue.initialization.repositories.AdapterMetaDataSingInRepository
import com.example.fefufit.glue.initialization.repositories.AdapterInitializationRepository
import com.example.initialization_impl.domain.repositories.InitializationFeatureRepository
import com.example.initialization_impl.domain.repositories.SingInFeatureMetaDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InitializationRepositoryModule {

    @Binds
    fun bindInitializationRepository(
        initializationRepository: AdapterInitializationRepository
    ):InitializationFeatureRepository

    @Binds
    fun bindAdapterMetaDataSingInRepository(
        singInFeatureMetaRepository: AdapterMetaDataSingInRepository
    ): SingInFeatureMetaDataRepository

}