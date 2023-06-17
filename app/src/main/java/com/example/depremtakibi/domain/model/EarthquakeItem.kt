package com.example.depremtakibi.domain.model

data class EarthquakeItem(
    val earthquake_id: String,
    val title: String,
    val magnitude: Double,
    val time: String,
    val cityName: String? = null,
)

