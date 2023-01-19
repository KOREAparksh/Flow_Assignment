package com.flow.assignment.service

import com.flow.assignment.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var instance: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()
    // 서버 주소
    private const val BASE_URL = BuildConfig.API_URL
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(MyInterceptor())
            //Todo timeout 상수화
        .connectTimeout(5000, TimeUnit.SECONDS)
        .readTimeout(5000, TimeUnit.SECONDS)
        .writeTimeout(5000, TimeUnit.SECONDS)
        .build()

    // SingleTon
    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }
        return instance!!
    }
}

 class MyInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request = chain.request()
            .newBuilder()
            //Todo key 상수화
            .addHeader("X-Naver-Client-Id", BuildConfig.API_ID)
            .addHeader("X-Naver-Client-Secret", BuildConfig.API_PW)
            .build()
        return chain.proceed(newRequest)
    }
}