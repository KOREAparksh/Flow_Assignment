package com.flow.assignment.repository

import com.flow.assignment.dto.MovieDto
import com.flow.assignment.service.api.MovieApi
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi
){

    suspend fun getMovies(query: String) : MovieDto{
        val api = movieApi.getMovies(query)
        val response = api.execute()

        return response.body()!!
    }

    suspend fun getMovies(query: String, startPoint: Int) : MovieDto{
        val api = movieApi.getMovies(query, startPoint)
        val response = api.execute()

        return response.body()!!
    }
}