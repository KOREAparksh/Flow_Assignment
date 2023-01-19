package com.flow.assignment

import android.util.Log
import com.flow.assignment.dto.MovieDto
import com.flow.assignment.service.MovieService
import com.flow.assignment.service.RetrofitClient
import junit.framework.TestCase.assertEquals
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun retrofitTest() {
        val retrofit: Retrofit = RetrofitClient.getInstance();
        val movieService  = retrofit.create(MovieService::class.java)
        var dto: MovieDto
        val result = movieService.getMovies("아이언맨")
        try {
            val response: Response<MovieDto> = result.execute()
            dto = response.body()!!
        }catch (e: Exception){
            dto = MovieDto("", 1,1,1)
        }
        println("123123")
        println(dto.toString())
    }
}