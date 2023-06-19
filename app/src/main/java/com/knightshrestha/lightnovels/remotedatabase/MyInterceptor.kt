package com.knightshrestha.lightnovels.remotedatabase


import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("x-hasura-admin-secret", "qmFFCgy8X05QncA1Vuo63CTCcaOho5VoLDqJS5OVcgDu4z2rT0Cq4uHM3764M2mb")
            .addHeader("content-type", "application/json")
            .build()
        return chain.proceed(request )
    }
}