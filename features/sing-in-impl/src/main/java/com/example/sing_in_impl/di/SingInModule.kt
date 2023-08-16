package com.example.sing_in_impl.di

import com.example.sing_in_api.SingInApi
import com.example.sing_in_impl.naigation.SingInImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SingInModule {
    @Binds
    fun bindSingInScreen(singInImp: SingInImpl): SingInApi
}