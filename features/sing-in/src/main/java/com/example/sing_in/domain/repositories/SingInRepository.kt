package com.example.sing_in.domain.repositories

import com.example.sing_in.domain.models.SingInDataModel
import com.example.sing_in.domain.models.SingInSuccessResponse

interface SingInRepository {
    suspend fun singIn(singInData: SingInDataModel): SingInSuccessResponse
}