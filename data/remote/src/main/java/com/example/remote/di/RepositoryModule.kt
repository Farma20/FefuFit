package com.example.remote.di

import com.example.fefufit.data.remote.repositories.EventsFefuRepository
import com.example.fefufit.data.remote.repositories.ServicesFefuRepository
import com.example.fefufit.data.remote.repositories.UserFefuRepository
import com.example.remote.EventsRepository
import com.example.remote.InitializationRepository
import com.example.remote.ServicesRepository
import com.example.remote.UserRepository
import com.example.remote.repositories.InitialFefuRepository
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
    abstract fun bindUserDataRepository(
        userDataRepository: UserFefuRepository
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindEventsDataRepository(
        eventsDataRepository: EventsFefuRepository
    ): EventsRepository

    @Binds
    @Singleton
    abstract fun bindServicesDataRepository(
        servicesDataRepository: ServicesFefuRepository
    ): ServicesRepository

    @Binds
    @Singleton
    abstract fun bindInitialRepository(
        initialRepository: InitialFefuRepository
    ): InitializationRepository
}