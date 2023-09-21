package com.example.timetable_impl.domain.repositories

interface TimeTableMetaDataRepository {
    suspend fun getUserTokenMetaData():String
}