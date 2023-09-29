package com.example.remote.repositories

import com.example.fefufit.data.remote.models.initial_data_models.DataSingInDataModel
import com.example.remote.InitializationDataRepository
import com.example.remote.data_source.FefuFitApi
import com.example.remote.models.initial_data_models.DataSingInResponse
import com.example.remote.models.initial_data_models.DataSingUpDataModel
import com.example.remote.models.initial_data_models.DataSingUpResponse
import com.example.remote.utils.WrapperRetrofitException
import javax.inject.Inject


class InitialFefuRepository @Inject constructor(
    private val fefuFitApi: FefuFitApi,
    private val exceptionClass: WrapperRetrofitException
) : InitializationDataRepository {
    override suspend fun singIn(singInData: DataSingInDataModel): DataSingInResponse =
        exceptionClass.wrapRetrofitException {
            fefuFitApi.singIn(singInData)
        }

    override suspend fun singUp(singUpData: DataSingUpDataModel): DataSingUpResponse =
        exceptionClass.wrapRetrofitException {
            fefuFitApi.singUp(singUpData)
        }

}