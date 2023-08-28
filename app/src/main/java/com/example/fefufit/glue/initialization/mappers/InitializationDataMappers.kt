package com.example.fefufit.glue.initialization.mappers

import com.example.fefufit.data.remote.models.initial_data_models.DataSingInDataModel
import com.example.fefufit.data.remote.models.initial_data_models.DataSingInSuccessResponse
import com.example.fefufit.data.remote.models.initial_data_models.DataSingUpDataModel
import com.example.initialization_impl.domain.models.FeatureInitialUserDataModel
import com.example.initialization_impl.domain.models.FeatureSingInDataModel
import com.example.initialization_impl.domain.models.FeatureSingInSuccessResponse
import com.example.initialization_impl.domain.models.FeatureSingUpDataModel

fun FeatureSingInDataModel.toDataSingInDataModel():DataSingInDataModel{
    return DataSingInDataModel(
        this.email,
        this.password
    )
}

fun FeatureSingUpDataModel.toDataSingUpDataModel():DataSingUpDataModel{
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

fun DataSingInSuccessResponse.toFeatureSingInSuccessResponse(): FeatureSingInSuccessResponse {
    return FeatureSingInSuccessResponse(
        initialUserDataModel = FeatureInitialUserDataModel(
            this.initialUserDataModel.qrToken,
            this.initialUserDataModel.token,
            this.initialUserDataModel.type,
        ),
        status = this.status
    )
}