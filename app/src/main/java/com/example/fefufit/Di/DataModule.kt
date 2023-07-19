package com.example.fefufit.Di

import com.example.fefufit.Data.Remote.API.FefuFitApi
import com.example.fefufit.Data.Remote.Repositorys.FefuInitialRepository
import com.example.fefufit.Domain.Repositorys.InitializationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideInitialRepository(api: FefuFitApi): InitializationRepository {
        return FefuInitialRepository(api)
    }
}