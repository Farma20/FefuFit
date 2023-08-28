package com.example.main_impl.domain.repositories

interface MainMetaDataRepository {
    suspend fun getUserTokenMetaData():String
}