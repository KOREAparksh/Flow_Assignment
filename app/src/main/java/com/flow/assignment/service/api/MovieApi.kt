package com.flow.assignment.service.api

import com.flow.assignment.dto.MovieDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {
    @GET("movie.json")
    suspend fun getMovies(
        @Query("query", encoded = true) name: String,
        @Query("start") startPoint: Int = 1
    ): Response<MovieDto>
}