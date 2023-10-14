package com.example.remote.di

import com.example.remote.repositories.EventsFefuRepository
import com.example.remote.repositories.ServicesFefuRepository
import com.example.remote.repositories.UserFefuRepository
import com.example.remote.EventsDataRepository
import com.example.remote.InitializationDataRepository
import com.example.remote.RefreshDataRepository
import com.example.remote.ServicesDataRepository
import com.example.remote.UserDataRepository
import com.example.remote.repositories.InitialFefuRepository
import com.example.remote.repositories.RefreshFefuRepository
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
    ): UserDataRepository

    @Binds
    @Singleton
    abstract fun bindEventsDataRepository(
        eventsDataRepository: EventsFefuRepository
    ): EventsDataRepository

    @Binds
    @Singleton
    abstract fun bindServicesDataRepository(
        servicesDataRepository: ServicesFefuRepository
    ): ServicesDataRepository

    @Binds
    @Singleton
    abstract fun bindInitialRepository(
        initialRepository: InitialFefuRepository
    ): InitializationDataRepository

    @Binds
    @Singleton
    abstract fun bindRefreshRepository(
        refreshRepository: RefreshFefuRepository
    ):RefreshDataRepository
}