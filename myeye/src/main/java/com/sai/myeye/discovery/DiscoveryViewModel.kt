package com.sai.myeye.discovery

import androidx.lifecycle.liveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sai.myeye.bean.Discovery
import com.sai.myeye.http.ApiService
import com.sai.myeye.utils.LogUtils
import java.lang.Exception
import java.security.cert.Extension

class DiscoveryViewModel(repository: DiscoveryRepository) : ViewModel() {

     var nextPageUrl: String? = null
    private var discoveryData = MutableLiveData<String>()

    val result = Transformations.switchMap(discoveryData) { url ->
        liveData {
            val res = try {
                val data = repository.refreshDiscovery(url)
                Result.success(data)
            }catch (e:Exception){
                Result.failure<Discovery>(e)
            }
            emit(res)
        }
    }

    fun onRefresh() {
        discoveryData.value = ApiService.DISCOVERY_URL
    }

    fun onLoadMore() {
        discoveryData.value = nextPageUrl ?: ""

    }

}
