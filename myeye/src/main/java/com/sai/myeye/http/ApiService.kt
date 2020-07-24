package com.sai.myeye.http

import com.sai.myeye.bean.Discovery
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    companion object {

        const val BASE_URL = "http://baobab.kaiyanapp.com/"

        const val DISCOVERY_URL = "${BASE_URL}api/v7/index/tab/discovery"

    }

    @GET
    fun getDiscovery(@Url url: String): Call<Discovery>
}