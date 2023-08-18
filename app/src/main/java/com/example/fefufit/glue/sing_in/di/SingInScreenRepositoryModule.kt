package com.example.fefufit.glue.sing_in.di

import com.example.fefufit.glue.main_screen.repositories.AdapterUserRepository
import com.example.fefufit.glue.sing_in.repositories.AdapterSingInRepository
import com.example.main_impl.domain.repositories.UserFeatureRepository
import com.example.sing_in_impl.domain.repositories.SingInFeatureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SingInScreenRepositoryModule {

    @Binds
    fun bindInitialRepository(
        initialRepository: AdapterSingInRepository
    ):SingInFeatureRepository
}