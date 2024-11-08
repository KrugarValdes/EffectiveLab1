package com.effectivelab1.heroapp.network

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class MarvelApiInterceptor(
    private val publicKey: String,
    private val privateKey: String,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val timestamp = System.currentTimeMillis().toString()
        val hash = generateHash(timestamp, privateKey, publicKey)

        val newUrl =
            originalUrl
                .newBuilder()
                .apply {
                    addQueryParameter("apikey", publicKey)
                    addQueryParameter("ts", timestamp)
                    addQueryParameter("hash", hash)
                }.build()

        val newRequest =
            originalRequest
                .newBuilder()
                .url(newUrl)
                .build()

        return chain.proceed(newRequest)
    }

    private fun generateHash(
        timestamp: String,
        privateKey: String,
        publicKey: String,
    ): String = "$timestamp$privateKey$publicKey".md5()
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}
