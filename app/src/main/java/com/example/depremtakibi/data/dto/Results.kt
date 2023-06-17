package com.example.depremtakibi.data.dto

import com.example.depremtakibi.domain.model.EarthquakeItem
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("created_at")
    val created_at: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_time")
    val date_time: String,
    @SerializedName("depth")
    val depth: Double,
    @SerializedName("earthquake_id")
    val earthquake_id: String,
    @SerializedName("geojson")
    val geojson: Geojson,
    @SerializedName("location_properties")
    val location_properties: LocationProperties,
    @SerializedName("location_tz")
    val location_tz: String,
    @SerializedName("mag")
    val mag: Double,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("rev")
    val rev: Any,
    @SerializedName("title")
    val title: String
)

fun Results.toEarthquakeItem(): EarthquakeItem{
    return EarthquakeItem(
        earthquake_id = earthquake_id,
        title = title,
        magnitude = mag,
        time = date_time,
        cityName = location_properties.epiCenter.name
    )

}

/*fun Results.toEarthquakeDetailItem():EarthquakeDetailItem{
    return  EarthquakeDetailItem(
        earthquake_id = earthquake_id,
        geoJson = geojson,
        closestCities = location_properties.closestCities.map { it.name },
        airports = location_properties.airports.map { it.name },
        title = title,
        population = location_properties.epiCenter.population
    )

}*/