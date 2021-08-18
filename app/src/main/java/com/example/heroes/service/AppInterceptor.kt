package com.example.heroes.service

import android.os.Build
import com.example.heroes.BuildConfig
import com.example.heroes.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }

}
