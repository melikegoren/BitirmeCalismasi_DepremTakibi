package com.example.depremtakibi.data.source

import android.util.Log
import com.example.depremtakibi.common.Resource
import com.example.depremtakibi.data.remote.EarthquakeService
import com.example.depremtakibi.data.dto.toEarthquakeItem
import com.example.depremtakibi.data.dto_detail.toEarthquakeDetailItem
import com.example.depremtakibi.domain.model.EarthquakeDetailItem
import com.example.depremtakibi.domain.model.EarthquakeItem
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: EarthquakeService,
) : RemoteDataSource {
    override suspend fun getEarthquakes(): Resource<List<EarthquakeItem>> =
        try {
            val earthquakes =
                apiService.getEarthquakes(0, 100).result?.map { it.toEarthquakeItem() }

            Resource.Success(earthquakes)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage?.toString())
        }

    override suspend fun getEarthquake(id: String): Resource<EarthquakeDetailItem> =
        try {
            val earthquake = apiService.getEarthquakeById(id).resultDetail.toEarthquakeDetailItem()
            Log.d("earthquake_id", apiService.getEarthquakeById(id).resultDetail.earthquake_id)
            Resource.Success(earthquake)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage?.toString())
        }
}