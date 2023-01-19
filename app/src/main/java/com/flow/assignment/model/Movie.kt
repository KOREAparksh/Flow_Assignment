package com.flow.assignment.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("lastBuildDate") val lastBuildDate: String,
    @SerializedName("total") val total: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("display") val display: Int
)