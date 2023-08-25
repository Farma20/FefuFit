package com.example.initialization_api

import com.example.feature_api.FeatureApi

interface InitializationApi:FeatureApi {
    val route:String
}