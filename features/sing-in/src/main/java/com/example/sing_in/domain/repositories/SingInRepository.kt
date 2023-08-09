package com.example.sing_in.domain.repositories

import com.example.sing_in.domain.models.SingInDataModel

interface SingInRepository {
    suspend fun singIn(singInData: SingInDataModel):SingInSuccessResponse
}