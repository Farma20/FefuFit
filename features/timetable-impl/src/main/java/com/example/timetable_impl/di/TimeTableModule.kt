package com.example.timetable_impl.di

import com.example.timetable_api.TimeTableApi
import com.example.timetable_impl.navigation.TimeTableImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TimeTableModule {
    @Binds
    fun bindTimeTableScreen(timeTablePageImpl: TimeTableImpl): TimeTableApi
}