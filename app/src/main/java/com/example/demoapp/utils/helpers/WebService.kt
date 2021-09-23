package com.example.demoapp.utils.helpers

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Remote web service helper
 */
object WebService {

    /**
     * Create an OkHttpBuilder for retrofit
     */
    fun createOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
    }

    /**
     * Create the retrofit web service
     */
    inline fun <reified T> createWebService(
        okHttpClientBuilder: OkHttpClient.Builder,
        url: String,
        vararg defaultHeaders: Pair<String, String>
    ): T {
        // add headers
        for (header in defaultHeaders) {
            val (headerName, headerValue) = header
            okHttpClientBuilder.addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().addHeader(
                        headerName,
                        headerValue
                    ).build()
                )
            }
        }

        // retrofit
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(T::class.java)
    }

}