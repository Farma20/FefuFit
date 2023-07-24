package com.example.fefufit.Di

import com.example.fefufit.Data.Remote.Repositorys.InitialFefuRepository
import com.example.fefufit.Domain.Repositorys.InitializationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindInitialRepository(
        initialRepository: InitialFefuRepository
    ):InitializationRepository
}