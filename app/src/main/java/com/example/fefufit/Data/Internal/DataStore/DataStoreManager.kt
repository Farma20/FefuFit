package com.example.fefufit.Data.Internal.DataStore

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.fefufit.Data.Internal.DataStore.Entities.AppInternalData
import com.example.fefufit.Data.Internal.DataStore.Entities.UserMetaData
import com.example.fefufit.Data.Internal.DataStore.Serializer.AppInternalSerializer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton




class DataStoreManager(
    private val appContext:Context,
    private val serializer: Serializer<AppInternalData>
) {
    private val Context.dataStore by dataStore("app-internal.json", serializer)
    private val appInternalDataStore = appContext.dataStore

    val data = appInternalDataStore.data

    suspend fun setUserMetaData(userMetaData: UserMetaData){
        appInternalDataStore.updateData {
            it.copy(userMetaData = userMetaData)
        }
    }

}