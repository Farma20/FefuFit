package com.example.sing_in.domain.repositories

import com.example.sing_in.domain.models.UserMetaData

interface DataStoreRepository {
    fun setUserMetaData(data:UserMetaData)
}