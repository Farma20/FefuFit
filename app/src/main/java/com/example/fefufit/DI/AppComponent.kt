package com.example.fefufit.DI

import com.example.fefufit.FefuFitApp
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(app: FefuFitApp)
}