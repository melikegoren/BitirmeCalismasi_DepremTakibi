package com.example.depremtakibi.data.mapper


import com.example.depremtakibi.domain.model.EarthquakeItem
import com.example.depremtakibi.presentation.home.HomeUiData
import javax.inject.Inject

class HomeListMapperImpl @Inject constructor() : HomeListMapper<EarthquakeItem, HomeUiData> {
    override fun map(input: List<EarthquakeItem>): List<HomeUiData> {
        return input.map { earthquake ->
            HomeUiData(
                eartquake_id = earthquake.earthquake_id,
                title = earthquake.title,
                magnitude = earthquake.magnitude,
                time = earthquake.time,
                cityName = earthquake.cityName.toString()
            )
        }

    }
}