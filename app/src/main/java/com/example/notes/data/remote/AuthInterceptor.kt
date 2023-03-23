package com.example.notes.data.remote

import com.example.notes.common.Preferences
import okhttp3.Interceptor
import okhttp3.Response

import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val preferences: Preferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token: String? = preferences.getToken()
        val request = chain.request().newBuilder()
        request.addHeader("authorization", "Bearer $token")

        return chain.proceed(request.build())
    }
}