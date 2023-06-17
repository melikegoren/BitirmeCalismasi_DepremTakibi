package com.example.depremtakibi.data.dto_detail

import com.google.gson.annotations.SerializedName

data class EarthquakeDetail(
    val desc: String,
    val httpStatus: Int,
    @SerializedName("result")
    val resultDetail: ResultDetail,
    val serverloadms: Int,
    val status: Boolean,
)