package com.example.depremtakibi.di

import com.example.depremtakibi.data.mapper.*
import com.example.depremtakibi.domain.model.EarthquakeDetailItem
import com.example.depremtakibi.domain.model.EarthquakeItem
import com.example.depremtakibi.presentation.detail.DetailsUiData
import com.example.depremtakibi.presentation.home.HomeUiData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindHomeListMapper(homeListMapperImpl: HomeListMapperImpl): HomeListMapper<EarthquakeItem, HomeUiData>


    @Binds
    @ViewModelScoped
    abstract fun bindDetailsMapper(detailsMapperImpl: DetailsMapperImpl): DetailsMapper<EarthquakeDetailItem, DetailsUiData>

}