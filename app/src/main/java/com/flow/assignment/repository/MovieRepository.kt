package com.flow.assignment.repository

import com.flow.assignment.dto.MovieDto
import com.flow.assignment.service.api.MovieApi
import com.flow.assignment.service.api.RetrofitClient
import retrofit2.Retrofit

class MovieRepository {
    private val retrofit: Retrofit = RetrofitClient.getInstance()
    private val movieApi: MovieApi =  retrofit.create(MovieApi::class.java)

    fun getMovies(movieTitle: String) : MovieDto{
        val api = movieApi.getMovies(movieTitle)
        val response = api.execute()

        return response.body()!!
    }

    fun getMovies(movieTitle: String, startPoint: Int) : MovieDto{
        val api = movieApi.getMovies(movieTitle, startPoint)
        val response = api.execute()

        return response.body()!!
    }
}