package com.sai.jetpack.http

import android.net.sip.SipErrorCode.TIME_OUT
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.sai.jetpack.BaseUrl
import com.sai.jetpack.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class HttpManager {

//
//    val retrofitService: ApiService = getService(BaseUrl, ApiService::class.java)
//
//    private fun <T> getService(url: String, service: Class<T>): T = create(url).create(service)
//
//
//    private fun create(url: String): Retrofit {
//        // okHttpClientBuilder
//        val okHttpClientBuilder = OkHttpClient().newBuilder().apply {
//            connectTimeout(5, TimeUnit.SECONDS)
//            readTimeout(5, TimeUnit.SECONDS)
//
//            // loggingInterceptor
//            val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLogger()).apply {
//                level = BODY
//            }
//            addInterceptor(httpLoggingInterceptor)
//        }
//
//        return RetrofitBuild(
//            url = url,
//            client = okHttpClientBuilder.build(),
//            gsonFactory = GsonConverterFactory.create(),
//            coroutineCallAdapterFactory = CoroutineCallAdapterFactory()
//        )
//            .retrofit
//    }
//
//
//    class RetrofitBuild(
//        url: String, client: OkHttpClient,
//        gsonFactory: GsonConverterFactory,
//        coroutineCallAdapterFactory: CoroutineCallAdapterFactory
//    ) {
//        val retrofit: Retrofit = Retrofit.Builder().apply {
//            baseUrl(url)
//            client(client)
//            addConverterFactory(gsonFactory)
//        }.build()
//    }
//
//    class HttpLogger : HttpLoggingInterceptor.Logger {
//        override fun log(message: String) {
//            Log.e("--Http--", message)
//        }
//    }


    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.BASIC
            }

            builder.addInterceptor(logging)
                .connectTimeout(5, TimeUnit.SECONDS)

            handleBuilder(builder)

            return builder.build()
        }

    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)

    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }
}