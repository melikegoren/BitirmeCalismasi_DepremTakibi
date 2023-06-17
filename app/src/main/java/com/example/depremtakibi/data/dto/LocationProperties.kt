package com.example.depremtakibi.data.dto

data class LocationProperties(
    val airports: List<Airport>,
    val closestCities: List<ClosestCity>,
    val closestCity: ClosestCity,
    val epiCenter: EpiCenter
)