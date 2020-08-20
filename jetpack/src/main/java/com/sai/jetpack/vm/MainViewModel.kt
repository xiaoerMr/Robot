package com.sai.jetpack.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sai.jetpack.bean.ResBannerItem
import com.sai.jetpack.http.HttpManagerImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel : ViewModel() {

    //postBanner
    private val banner = MutableLiveData<String>()

    var resultBanner = Transformations.switchMap(banner) {
        liveData {
            val res = try {
                val data = requestDiscovery()
                Result.success(data.data)
            } catch (e: Exception) {
                Result.failure<List<ResBannerItem>>(e)
            }
            emit(res)
        }
    }
    fun onRefreshBanner(){
        banner.value  = "start"
    }


    private suspend fun requestDiscovery() = withContext(Dispatchers.IO) {
        val response = HttpManagerImpl().service.getBannerData()
        //保存本地

        response
    }
}