package com.flow.assignment.service.api

import com.flow.assignment.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class NaverInterceptor : Interceptor {
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