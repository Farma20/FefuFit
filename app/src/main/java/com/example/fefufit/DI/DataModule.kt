package com.example.fefufit.DI

import com.example.fefufit.Data.Remote.API.FefuFitApi
import com.example.fefufit.Data.Remote.Repositorys.FefuInitialRepository
import com.example.fefufit.Domain.Repositorys.InitializationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideInitialRepository(api: FefuFitApi):InitializationRepository{
        return FefuInitialRepository(api)
    }
}