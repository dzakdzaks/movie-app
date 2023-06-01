package com.dzakdzaks.movieapp.common.network.interceptor

import com.dzakdzaks.movieapp.common.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.TOKEN}")
            .build()
        return chain.proceed(request)
    }
}
