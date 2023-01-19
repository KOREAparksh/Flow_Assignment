package com.flow.assignment.repository

import com.flow.assignment.dto.MovieDto
import com.flow.assignment.service.api.MovieApi
import com.flow.assignment.service.api.RetrofitClient
import retrofit2.Retrofit
import retrofit2.create

class MovieRepository {
    private var retrofit: Retrofit = RetrofitClient.getInstance()
    private var movieApi: MovieApi =  retrofit.create(MovieApi::class.java)

    fun getMovies(movieTitle: String) : MovieDto{
        var movieDto: MovieDto
        val api = movieApi.getMovies(movieTitle)
        val response = api.execute()

        return response.body()!!
    }

    fun getMovies(movieTitle: String, startPoint: Int) : MovieDto{
        var movieDto: MovieDto
        val api = movieApi.getMovies(movieTitle, startPoint)
        val response = api.execute()

        return response.body()!!
    }
}