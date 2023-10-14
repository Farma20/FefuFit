package com.example.fefufit.glue.refresh_token.di


import com.example.feature_api.refresh_api.FeatureRefreshTokenApi
import com.example.fefufit.glue.refresh_token.repositories.AdapterRefreshRepository
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
    fun bindRefreshRepository(
        refreshRepository: AdapterRefreshRepository
    ): FeatureRefreshTokenApi
}