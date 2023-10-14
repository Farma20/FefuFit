package com.example.fefufit.glue.refresh_token.di

import com.example.fefufit.glue.main_screen.repositories.AdapterUserRepository
import com.example.main_impl.domain.repositories.UserFeatureRepository
import com.example.remote.RefreshDataRepository
import com.example.remote.repositories.RefreshFefuRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RefreshTokenModule {
    @Binds
    fun bindUserRepository(
        refreshRepository: RefreshFefuRepository
    ): RefreshDataRepository
}