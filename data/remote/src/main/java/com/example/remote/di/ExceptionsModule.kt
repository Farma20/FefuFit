package com.example.remote.di

import com.example.remote.utils.WrapperRetrofitException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExceptionsModule {

    @Provides
    @Singleton
    fun provideExceptionsClass():WrapperRetrofitException = WrapperRetrofitException()
}