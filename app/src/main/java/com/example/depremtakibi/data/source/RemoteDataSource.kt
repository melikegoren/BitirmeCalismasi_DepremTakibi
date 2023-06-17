package com.example.depremtakibi.data.source

import com.example.depremtakibi.common.Resource
import com.example.depremtakibi.domain.model.EarthquakeDetailItem
import com.example.depremtakibi.domain.model.EarthquakeItem

interface RemoteDataSource {
    suspend fun getEarthquakes(): Resource<List<EarthquakeItem>>
    suspend fun getEarthquake(id: String): Resource<EarthquakeDetailItem>
}