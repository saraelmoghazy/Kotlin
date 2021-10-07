package com.example.bootcamp.network

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Sara Elmoghazy
 * Interceptor responsible for adding common headers to the request.
 */
internal class HeadersInterceptor(headers: Map<String, String>) : Interceptor {

    private var headers: Map<String, String>? = null

    init {
        this.headers = headers
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        if (headers != null) {
            for ((key, value) in headers!!) {
                requestBuilder.addHeader(key, value)
            }
        }

        return chain.proceed(requestBuilder.build())
    }
}
