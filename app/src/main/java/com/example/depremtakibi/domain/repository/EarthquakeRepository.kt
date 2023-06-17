package com.example.depremtakibi.domain.repository

import com.example.depremtakibi.common.Resource
import com.example.depremtakibi.data.dto.Earthquake
import com.example.depremtakibi.data.dto.Results
import com.example.depremtakibi.data.dto_detail.EarthquakeDetail
import com.example.depremtakibi.data.dto_detail.ResultDetail
import com.example.depremtakibi.domain.model.EarthquakeDetailItem
import com.example.depremtakibi.domain.model.EarthquakeItem
import kotlinx.coroutines.flow.Flow

interface EarthquakeRepository {

    suspend fun getEarthquakes(): Resource<List<EarthquakeItem?>>

    suspend fun getEarthquake(id: String): Resource<EarthquakeDetailItem>
}