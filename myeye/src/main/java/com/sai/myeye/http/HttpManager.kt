package com.sai.myeye.http

import com.sai.myeye.http.core.ServiceCreator
import retrofit2.await

class HttpManager {

    private val apiService = ServiceCreator.create(ApiService::class.java)


    suspend fun fetchDiscovery(url: String) = apiService.getDiscovery(url).await()


}