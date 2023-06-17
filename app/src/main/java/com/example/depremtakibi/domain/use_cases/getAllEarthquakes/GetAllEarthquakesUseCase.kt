package com.example.depremtakibi.domain.use_cases.getAllEarthquakes

import com.example.depremtakibi.common.Resource
import com.example.depremtakibi.domain.model.EarthquakeItem
import kotlinx.coroutines.flow.Flow

interface GetAllEarthquakesUseCase {
    operator fun invoke(): Flow<Resource<List<EarthquakeItem>>>
}