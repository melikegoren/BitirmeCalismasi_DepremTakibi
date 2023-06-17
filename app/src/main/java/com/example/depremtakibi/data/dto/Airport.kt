package com.example.depremtakibi.data.dto

data class Airport(
    val code: String,
    val coordinates: Coordinates,
    val distance: Double,
    val name: String,
)