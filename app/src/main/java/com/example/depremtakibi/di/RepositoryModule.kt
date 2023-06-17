package com.example.depremtakibi.di

import com.example.depremtakibi.data.repository.EarthquakeRepositoryImpl
import com.example.depremtakibi.domain.repository.EarthquakeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideEarthquakeRepository(earthquakeRepositoryImpl: EarthquakeRepositoryImpl): EarthquakeRepository
}