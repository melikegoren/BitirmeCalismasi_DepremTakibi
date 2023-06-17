package com.example.depremtakibi.di

import com.example.depremtakibi.domain.use_cases.getAllEarthquakes.GetAllEarthquakesUseCase
import com.example.depremtakibi.domain.use_cases.getAllEarthquakes.GetAllEarthquakesUseCaseImpl
import com.example.depremtakibi.domain.use_cases.getEarthquakeById.GetEarthquakeUseCase
import com.example.depremtakibi.domain.use_cases.getEarthquakeById.GetEarthquakeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllEarthquakesUseCase(getAllEarthquakesUseCaseImpl: GetAllEarthquakesUseCaseImpl): GetAllEarthquakesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetEarthquakeUseCase(getEarthquakeUseCaseImpl: GetEarthquakeUseCaseImpl): GetEarthquakeUseCase
}