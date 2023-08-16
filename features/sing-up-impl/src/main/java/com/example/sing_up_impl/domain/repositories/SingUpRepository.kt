package com.example.sing_up_impl.domain.repositories

import com.example.sing_up_impl.domain.models.SingUpDataModel

interface SingUpRepository {
    suspend fun singUp(singUpData: SingUpDataModel): Map<String, String>
}