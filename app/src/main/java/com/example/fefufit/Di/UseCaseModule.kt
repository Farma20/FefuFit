package com.example.fefufit.Di

import com.example.fefufit.Domain.Repositorys.InitializationRepository
import com.example.fefufit.Domain.UseCases.Initial.SingInUseCase
import com.example.fefufit.Domain.UseCases.Initial.SingUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideSingInUseCase(repository: InitializationRepository):SingInUseCase{
        return SingInUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideSingUpUseCase(repository: InitializationRepository): SingUpUseCase {
        return SingUpUseCase(repository)
    }
}