package com.example.fefufit.Di

import com.example.fefufit.Data.Remote.Repositorys.EventsFefuRepository
import com.example.fefufit.Data.Remote.Repositorys.InitialFefuRepository
import com.example.fefufit.Data.Remote.Repositorys.UserDataFefuRepository
import com.example.fefufit.Domain.Repositorys.EventsRepository
import com.example.fefufit.Domain.Repositorys.InitializationRepository
import com.example.fefufit.Domain.Repositorys.UserDataRepository
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

    @Binds
    @Singleton
    abstract fun bindUserDataRepository(
        userDataRepository: UserDataFefuRepository
    ):UserDataRepository

    @Binds
    @Singleton
    abstract fun bindEventsDataRepository(
        eventsDataRepository: EventsFefuRepository
    ):EventsRepository
}