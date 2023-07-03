package com.example.fefufit

import android.app.Application
import com.example.fefufit.DI.AppComponent
import com.example.fefufit.DI.DaggerAppComponent

class FefuFitApp:Application() {
    companion object {
        lateinit var instance: FefuFitApp
            private set
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    private fun initComponent(){
        appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
    }
}