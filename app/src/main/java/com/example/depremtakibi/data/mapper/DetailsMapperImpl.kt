package com.example.depremtakibi.data.mapper

import com.example.depremtakibi.domain.model.EarthquakeDetailItem
import com.example.depremtakibi.presentation.detail.DetailsUiData
import javax.inject.Inject

class DetailsMapperImpl @Inject constructor() : DetailsMapper<EarthquakeDetailItem, DetailsUiData> {
    override fun map(input: EarthquakeDetailItem): DetailsUiData {
        return input.let {
            DetailsUiData(
                title = it.title,
                population = it.population,
                geojson = it.geoJson,
                closestCities = it.closestCities,
                airports = it.airports
            )
        }
    }
}