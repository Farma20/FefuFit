package com.example.fefufit.glue.timetable_screen.di

import com.example.fefufit.glue.main_screen.repositories.AdapterEventsRepository
import com.example.fefufit.glue.main_screen.repositories.AdapterMetaDataRepository
import com.example.fefufit.glue.main_screen.repositories.AdapterServicesRepository
import com.example.fefufit.glue.main_screen.repositories.AdapterUserRepository
import com.example.fefufit.glue.timetable_screen.repositories.AdapterEventsTimeTableRepository
import com.example.fefufit.glue.timetable_screen.repositories.AdapterMetaDataTimeTableRepository
import com.example.main_impl.domain.repositories.EventsFeatureRepository
import com.example.main_impl.domain.repositories.MainMetaDataRepository
import com.example.main_impl.domain.repositories.ServicesFeatureRepository
import com.example.main_impl.domain.repositories.UserFeatureRepository
import com.example.timetable_impl.domain.repositories.TimeTableEventsFeatureRepository
import com.example.timetable_impl.domain.repositories.TimeTableMetaDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TimeTableRepositoryModule {
    @Binds
    fun bindEventsTimeTableRepository(
        eventsRepository: AdapterEventsTimeTableRepository
    ): TimeTableEventsFeatureRepository

    @Binds
    fun bindMetaDataTimeTableRepository(
        metaDataRepository: AdapterMetaDataTimeTableRepository
    ): TimeTableMetaDataRepository
}