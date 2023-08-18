package com.example.fefufit.glue.sing_in.repositories

import com.example.remote.InitializationRepository
import com.example.sing_in_impl.domain.models.SingInDataModel
import com.example.sing_in_impl.domain.models.SingInSuccessResponse
import com.example.sing_in_impl.domain.repositories.SingInRepository
import javax.inject.Inject

class AdapterSingInRepository @Inject constructor(
    private val initialRepository: InitializationRepository
):SingInRepository {
    override suspend fun singIn(singInData: SingInDataModel): SingInSuccessResponse {
         return initialRepository
             .singIn(
                 singInData as com.example.fefufit.data.remote.models.initial_data_models.SingInDataModel
             ) as SingInSuccessResponse
    }
}