package com.example.remote.repositories

import com.example.remote.RefreshDataRepository
import com.example.remote.data_source.FefuFitApi
import com.example.remote.models.initial_data_models.DataSingInResponse
import com.example.remote.models.initial_data_models.RefreshTokenDTO
import com.example.remote.utils.WrapperRetrofitException
import javax.inject.Inject

class RefreshFefuRepository @Inject constructor(
    private val api: FefuFitApi,
    private val exceptionClass: WrapperRetrofitException
): RefreshDataRepository {
    override suspend fun refreshToken(refreshToken: RefreshTokenDTO): DataSingInResponse =
        exceptionClass.wrapRetrofitException{
            api.refreshToken(refreshToken)
        }
}