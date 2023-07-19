package com.example.fefufit.DI_Dagger2

import com.example.fefufit.FefuFitApp
import com.example.fefufit.Presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, DataModule::class])
@Singleton
interface AppComponent {
    fun inject(target: FefuFitApp)

    fun inject(target: MainActivity)
}