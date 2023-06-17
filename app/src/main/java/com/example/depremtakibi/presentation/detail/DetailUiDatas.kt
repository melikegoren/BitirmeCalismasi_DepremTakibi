package com.example.depremtakibi.presentation.detail

import com.example.depremtakibi.data.dto.Airport
import com.example.depremtakibi.data.dto.ClosestCity
import com.example.depremtakibi.data.dto.Geojson


data class DetailsUiData(
    val title: String?,
    val population: Int?,
    val geojson: Geojson,
    val closestCities: List<ClosestCity?>?,
    val airports: List<Airport?>?,
)