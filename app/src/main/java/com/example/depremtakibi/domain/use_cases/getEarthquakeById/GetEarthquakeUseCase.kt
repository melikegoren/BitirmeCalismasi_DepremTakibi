package com.example.depremtakibi.domain.use_cases.getEarthquakeById

import com.example.depremtakibi.common.Resource
import com.example.depremtakibi.domain.model.EarthquakeDetailItem
import kotlinx.coroutines.flow.Flow

interface GetEarthquakeUseCase {
    operator fun invoke(id: String): Flow<Resource<EarthquakeDetailItem>>
}