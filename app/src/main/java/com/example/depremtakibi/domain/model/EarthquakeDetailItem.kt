package com.example.depremtakibi.domain.model

import com.example.depremtakibi.data.dto.Airport
import com.example.depremtakibi.data.dto.ClosestCity
import com.example.depremtakibi.data.dto.Geojson

data class EarthquakeDetailItem(
    val earthquake_id: String,
    val geoJson: Geojson,
    val closestCities: List<ClosestCity>,
    val airports: List<Airport>,
    val title: String,
    val population: Int,
)



