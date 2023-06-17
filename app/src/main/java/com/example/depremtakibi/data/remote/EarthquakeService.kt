package com.example.depremtakibi.data.remote

import com.example.depremtakibi.data.dto.Earthquake
import com.example.depremtakibi.data.dto_detail.EarthquakeDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthquakeService {
    @GET("deprem/kandilli/live/")
    suspend fun getEarthquakes(@Query("skip") skip: Int, @Query("limit") limit: Int): Earthquake

    @GET("deprem/data/get/")
    suspend fun getEarthquakeById(@Query("earthquake_id") id: String): EarthquakeDetail
}

