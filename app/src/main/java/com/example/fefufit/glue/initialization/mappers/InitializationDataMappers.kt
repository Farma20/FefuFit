package com.example.fefufit.glue.initialization.mappers

import com.example.fefufit.data.remote.models.initial_data_models.DataSingInDataModel
import com.example.initialization_impl.domain.models.FeatureSingInDataModel
import com.example.initialization_impl.domain.models.FeatureSingInResponse
import com.example.initialization_impl.domain.models.FeatureSingUpDataModel
import com.example.initialization_impl.domain.models.FeatureSingUpResponse
import com.example.initialization_impl.domain.models.UserMeta
import com.example.remote.models.initial_data_models.DataSingInResponse
import com.example.remote.models.initial_data_models.DataSingUpDataModel
import com.example.remote.models.initial_data_models.DataSingUpResponse

fun FeatureSingInDataModel.toDataSingInDataModel():DataSingInDataModel{
    return DataSingInDataModel(
        this.email,
        this.password
    )
}

fun FeatureSingUpDataModel.toDataSingUpDataModel(): DataSingUpDataModel {
    return DataSingUpDataModel(
        this.birthdate,
        this.email,
        this.firstName,
        this.gender,
        this.password,
        this.phoneNumber,
        this.secondName,
        this.status,
        this.thirdName
    )
}

fun DataSingUpResponse.toFeatureSingUpResponse():FeatureSingUpResponse{
    return FeatureSingUpResponse(
        detail = this.detail
    )
}

fun DataSingInResponse.toFeatureSingInSuccessResponse(): FeatureSingInResponse {
    return FeatureSingInResponse(
        data = UserMeta(
            qrToken = this.data.qrToken,
            refreshToken = this.data.refreshToken,
            token = this.data.token,
            type = this.data.type
        ),
        status = this.status
    )
}