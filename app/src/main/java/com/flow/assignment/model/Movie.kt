package com.flow.assignment.model

import com.google.gson.annotations.SerializedName

class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("image") val image: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("director") val director: String,
    @SerializedName("actor") val actor: String,
    @SerializedName("userRating") val userRating: String
){
    override fun toString(): String {
        return "Movie(title='$title', link='$link', image='$image', subtitle='$subtitle', pubDate='$pubDate', director='$director', actor='$actor', userRating='$userRating')"
    }
}