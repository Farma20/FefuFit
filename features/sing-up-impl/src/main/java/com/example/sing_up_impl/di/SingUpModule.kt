package com.example.sing_up_impl.di

import com.example.sing_up_api.SingUpApi
import com.example.sing_up_impl.navigation.SingUpImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SingUpModule {
    @Binds
    fun bindSingUpScreen(singUpImp: SingUpImpl): SingUpApi
}