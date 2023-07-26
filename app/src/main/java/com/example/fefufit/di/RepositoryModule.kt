package com.example.fefufit.di

import com.example.fefufit.data.remote.repositories.EventsFefuRepository
import com.example.fefufit.data.remote.repositories.InitialFefuRepository
import com.example.fefufit.data.remote.repositories.ServicesFefuRepository
import com.example.fefufit.data.remote.repositories.UserDataFefuRepository
import com.example.fefufit.domain.repositories.EventsRepository
import com.example.fefufit.domain.repositories.InitializationRepository
import com.example.fefufit.domain.repositories.ServicesRepository
import com.example.fefufit.domain.repositories.UserDataRepository
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

    @Binds
    @Singleton
    abstract fun bindServicesDataRepository(
        servicesDataRepository: ServicesFefuRepository
    ):ServicesRepository
}