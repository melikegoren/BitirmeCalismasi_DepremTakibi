package com.example.depremtakibi.data.repository

import com.example.depremtakibi.common.Resource
import com.example.depremtakibi.data.source.RemoteDataSource
import com.example.depremtakibi.domain.model.EarthquakeDetailItem
import com.example.depremtakibi.domain.repository.EarthquakeRepository
import com.example.depremtakibi.domain.model.EarthquakeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EarthquakeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,

    ) : EarthquakeRepository {
    override suspend fun getEarthquakes(): Resource<List<EarthquakeItem?>> =
        withContext(Dispatchers.IO) {
            try {
                remoteDataSource.getEarthquakes()
            } catch (e: Exception) {
                Resource.Error(e.localizedMessage?.toString())
            }
        }

    override suspend fun getEarthquake(id: String): Resource<EarthquakeDetailItem> =
        withContext(Dispatchers.IO) {
            try {
                remoteDataSource.getEarthquake(id)
            } catch (e: Exception) {
                Resource.Error(e.localizedMessage?.toString())
            }
        }
}