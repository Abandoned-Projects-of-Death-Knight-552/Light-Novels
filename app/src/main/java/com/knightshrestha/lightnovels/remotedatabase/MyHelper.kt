package com.knightshrestha.lightnovels.remotedatabase

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyHelper {
    private const val base_url = "https://light-novels-neon.hasura.app/"

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(base_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val Api: MyAPI by lazy {
        retrofit.create(MyAPI::class.java)
    }
}