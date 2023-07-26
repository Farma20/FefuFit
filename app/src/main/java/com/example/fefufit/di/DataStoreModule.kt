package com.example.fefufit.di

import android.content.Context
import androidx.datastore.core.Serializer
import com.example.fefufit.data.internal.data_store.DataStoreManager
import com.example.fefufit.data.internal.data_store.entities.AppInternalData
import com.example.fefufit.data.internal.data_store.serializer.AppInternalSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val SERIALIZER_NAME = "AppInternalSerializer"

    @Provides
    @Singleton
    @Named(SERIALIZER_NAME)
    fun provideSerializer():Serializer<AppInternalData>{
        return AppInternalSerializer()
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(
        @ApplicationContext appContext: Context,
        @Named(SERIALIZER_NAME) serializer: Serializer<AppInternalData>
    ): DataStoreManager{
        return DataStoreManager(appContext, serializer)
    }
}