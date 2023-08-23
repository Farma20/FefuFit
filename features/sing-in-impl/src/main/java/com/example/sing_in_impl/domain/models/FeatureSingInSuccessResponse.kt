package com.example.sing_in_impl.domain.models


data class FeatureSingInSuccessResponse(
    val initialUserDataModel: FeatureInitialUserDataModel,
    val status: String
)