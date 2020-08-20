package com.sai.jetpack.http

import com.sai.jetpack.BaseUrl
import okhttp3.OkHttpClient

class HttpManagerImpl :HttpManager() {


    val service by lazy { getService(ApiService::class.java, BaseUrl) }


    override fun handleBuilder(builder: OkHttpClient.Builder) {

    }


}