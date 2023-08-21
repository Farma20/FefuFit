package com.example.fefufit.glue.initialization.di

import com.example.fefufit.glue.initialization.repositories.AdapterSingInRepository
import com.example.fefufit.glue.initialization.repositories.AdapterSingUpRepository
import com.example.sing_in_impl.domain.repositories.SingInFeatureRepository
import com.example.sing_up_impl.domain.repositories.SingUpFeatureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SingInScreenRepositoryModule {

    @Binds
    fun bindSingInRepository(
        singInRepository: AdapterSingInRepository
    ):SingInFeatureRepository

    @Binds
    fun bindSingUpRepository(
        singUpRepository: AdapterSingUpRepository
    ): SingUpFeatureRepository
}