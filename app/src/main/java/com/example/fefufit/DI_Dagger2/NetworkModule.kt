package com.example.fefufit.DI_Dagger2

import com.example.fefufit.Data.Remote.API.FefuFitApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object{
        private const val BASE_URL = "hostName"
    }

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrlString():String = "https://fefufit.dvfu.ru"

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named(BASE_URL)baseUrl:String
    )
    :Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
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