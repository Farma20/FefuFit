package com.example.fefufit.glue.timetable_screen.repositories

import com.example.data_store.DataStoreRepository
import com.example.main_impl.domain.repositories.MainMetaDataRepository
import com.example.timetable_impl.domain.repositories.TimeTableMetaDataRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AdapterMetaDataTimeTableRepository @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
):TimeTableMetaDataRepository {
    override suspend fun getUserTokenMetaData(): String {
        return dataStoreRepository.getUserMetaData().first().userMetaData.userToken!!
    }
}