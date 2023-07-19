package com.example.fefufit.Di

import com.example.fefufit.Data.Remote.API.FefuFitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fefufit.dvfu.ru")
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): FefuFitApi{
        return retrofit.create(FefuFitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptors: ArrayList<Interceptor>):OkHttpClient{
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
        interceptors.forEach{
            clientBuilder.addInterceptor(it)
        }
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideInterceptors():ArrayList<Interceptor>{
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        interceptors.add(loggingInterceptor)
        return interceptors
    }

}