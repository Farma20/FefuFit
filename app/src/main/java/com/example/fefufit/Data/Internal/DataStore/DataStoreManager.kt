package com.example.fefufit.Data.Internal.DataStore

import android.content.Context
import androidx.datastore.dataStore
import com.example.fefufit.Data.Internal.DataStore.Entities.UserMetaData
import com.example.fefufit.Data.Internal.DataStore.Serializer.AppInternalSerializer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by dataStore("app-internal.json", AppInternalSerializer)

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext appContext:Context) {

    private val appInternalDataStore = appContext.dataStore

    val appInternalData = appInternalDataStore.data

    suspend fun setUserMetaData(userMetaData: UserMetaData){
        appInternalDataStore.updateData {
            it.copy(userMetaData = userMetaData)
        }
    }

}