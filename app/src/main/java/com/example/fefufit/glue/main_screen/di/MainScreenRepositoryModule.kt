package com.example.fefufit.glue.main_screen.di

import com.example.fefufit.glue.main_screen.repositories.AdapterUserRepository
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
}