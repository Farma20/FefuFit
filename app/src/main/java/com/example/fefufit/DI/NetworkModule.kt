package com.example.fefufit.DI

import com.example.fefufit.Data.Remote.API.FefuFitApi
import com.example.fefufit.FefuFitApp
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fefufit.dvfu.ru")
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): FefuFitApi{
        return retrofit.create(FefuFitApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptors: ArrayList<Interceptor>):OkHttpClient{
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
        interceptors.forEach{
            clientBuilder.addInterceptor(it)
        }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideInterceptors():ArrayList<Interceptor>{
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        interceptors.add(loggingInterceptor)
        return interceptors
    }
}