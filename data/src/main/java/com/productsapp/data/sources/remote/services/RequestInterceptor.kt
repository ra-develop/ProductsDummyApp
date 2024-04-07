package com.productsapp.data.sources.remote.services

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        val user = pref.get()
//        val token = user.apiToken
        val newRequest = chain.request().newBuilder()
//            .addHeader("Authorization", "Bearer $token")
//            .addHeader("Content-Type", "application/json; charset=utf-8")
            .build()
        return chain.proceed(newRequest)
    }
}