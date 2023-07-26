package com.example.fefufit.di

import com.example.fefufit.data.internal.data_store.DataStoreManager
import com.example.fefufit.domain.repositories.InitializationRepository
import com.example.fefufit.domain.use_cases.initial.SingInUseCase
import com.example.fefufit.domain.use_cases.initial.SingUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideSingInUseCase(
        repository: InitializationRepository,
        dataStoreManager:DataStoreManager
    ):SingInUseCase{
        return SingInUseCase(repository, dataStoreManager)
    }

    @Provides
    @ViewModelScoped
    fun provideSingUpUseCase(repository: InitializationRepository): SingUpUseCase {
        return SingUpUseCase(repository)
    }
}