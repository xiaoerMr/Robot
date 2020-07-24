package com.sai.myeye.discovery

import com.sai.myeye.http.HttpManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DiscoveryRepository {

    suspend fun refreshDiscovery(url: String) = requestDiscovery(url)

    //
    private suspend fun requestDiscovery(url: String) = withContext(Dispatchers.IO){
        val response = HttpManager().fetchDiscovery(url)
        //保存本地

        response
    }
}