package com.example.initialization_impl.di

import com.example.initialization_api.InitializationApi
import com.example.initialization_impl.naigation.InitializationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SingInModule {
    @Binds
    fun bindSingInScreen(singInImp: InitializationImpl): InitializationApi
}