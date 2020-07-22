package com.sai.robot.http

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object  ServiceCreator {

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("")
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
//        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapterFactory(GsonTypeAdapterFactory()).create()))


    private val retrofit = retrofitBuilder.build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}
