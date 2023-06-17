package com.example.depremtakibi.data.dto

import com.google.gson.annotations.SerializedName

data class Earthquake(
    val desc: String,
    val httpStatus: Int,
    val metadata: Metadata,
    @SerializedName("result")
    val result: List<Results>?,
    val serverloadms: Int,
    val status: Boolean
)