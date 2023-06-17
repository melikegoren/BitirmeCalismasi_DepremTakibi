package com.example.depremtakibi.di

import com.example.depremtakibi.data.remote.EarthquakeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    @ViewModelScoped
    fun provideEarthquakeApi(): EarthquakeService {
        return Retrofit.Builder()
            .baseUrl("https://api.orhanaydogdu.com.tr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EarthquakeService::class.java)
    }
}