package com.flow.assignment.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("lastBuildDate") val lastBuildDate: String,
    @SerializedName("total") val total: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("display") val display: Int
)