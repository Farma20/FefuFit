package com.example.fefufit.glue.main_screen.repositories

import com.example.data_store.DataStoreRepository
import com.example.main_impl.domain.repositories.MainMetaDataRepository
import javax.inject.Inject

class AdapterMetaDataRepository @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
):MainMetaDataRepository {
    override suspend fun getUserTokenMetaData(): String {
        var token:String? = null
        dataStoreRepository.getUserMetaData().collect{result->
            token = result.userMetaData.userToken
        }
        if (!token.isNullOrEmpty()){
            return token!!
        }
        else{
            throw Exception("incorrect token")
        }
    }

}