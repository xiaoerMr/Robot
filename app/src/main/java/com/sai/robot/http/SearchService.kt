package com.sai.robot.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface SearchService {

    @GET
    fun getDiscovery(@Url url: String): Call<List<String>>
}
