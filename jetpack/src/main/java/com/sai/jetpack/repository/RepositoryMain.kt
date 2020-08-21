package com.sai.jetpack.repository

import com.sai.jetpack.http.HttpManagerImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryMain {

     suspend fun requestDiscovery() = withContext(Dispatchers.IO) {
        val response = HttpManagerImpl().service.getBannerData()
        response
    }

}