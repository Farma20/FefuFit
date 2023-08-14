package com.example.main_api

import com.example.feature_api.FeatureApi

interface MainPageApi: FeatureApi {
    val route: String
}