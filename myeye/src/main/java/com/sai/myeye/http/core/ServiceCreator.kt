package com.sai.myeye.http.core

import com.sai.myeye.http.ApiService
import com.sai.myeye.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ServiceCreator {

    val okHttp = OkHttpClient.Builder()
        .addInterceptor(Log())
        .build()

    val builder = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL)
        .client(okHttp)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

//        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapterFactory(GsonTypeAdapterFactory()).create()))

    val retrofit = builder.build()

    fun <T> create(service: Class<T>): T = retrofit.create(service)

    class Log :Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            request.url()
         LogUtils().e("${ request.url()}")
            return chain.proceed(request)

        }
    }

}