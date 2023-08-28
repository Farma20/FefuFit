package com.example.fefufit.glue.main_screen.di

import com.example.fefufit.glue.main_screen.repositories.AdapterEventsRepository
import com.example.fefufit.glue.main_screen.repositories.AdapterMetaDataRepository
import com.example.fefufit.glue.main_screen.repositories.AdapterServicesRepository
import com.example.fefufit.glue.main_screen.repositories.AdapterUserRepository
import com.example.main_impl.domain.repositories.EventsFeatureRepository
import com.example.main_impl.domain.repositories.MainMetaDataRepository
import com.example.main_impl.domain.repositories.ServicesFeatureRepository
import com.example.main_impl.domain.repositories.UserFeatureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MainScreenRepositoryModule {

    @Binds
    fun bindUserRepository(
        userRepository: AdapterUserRepository
    ): UserFeatureRepository

    @Binds
    fun bindEventsRepository(
        eventsRepository: AdapterEventsRepository
    ): EventsFeatureRepository

    @Binds
    fun bindServicesRepository(
        servicesRepository: AdapterServicesRepository
    ): ServicesFeatureRepository

    @Binds
    fun bindMetaDataRepository(
        metaDataRepository: AdapterMetaDataRepository
    ): MainMetaDataRepository
}