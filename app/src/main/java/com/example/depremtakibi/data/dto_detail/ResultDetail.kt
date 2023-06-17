package com.example.depremtakibi.data.dto_detail

import com.example.depremtakibi.data.dto.Geojson
import com.example.depremtakibi.data.dto.LocationProperties
import com.example.depremtakibi.domain.model.EarthquakeDetailItem

data class ResultDetail(
    val _id: String,
    val created_at: Int,
    val date: String,
    val date_time: String,
    val depth: Double,
    val earthquake_id: String,
    val geojson: Geojson,
    val location_properties: LocationProperties,
    val location_tz: String,
    val mag: Double,
    val provider: String,
    val rev: Any,
    val title: String,
)

fun ResultDetail.toEarthquakeDetailItem(): EarthquakeDetailItem {
    return EarthquakeDetailItem(
        earthquake_id = earthquake_id,
        geoJson = geojson,
        closestCities = location_properties.closestCities,
        airports = location_properties.airports,
        title = title,
        population = location_properties.epiCenter.population
    )

}