package com.sai.jetpack.vm

import android.util.Log
import androidx.lifecycle.*
import com.sai.jetpack.bean.RequestBean
import com.sai.jetpack.bean.ResBannerItem
import com.sai.jetpack.repository.NetResult
import com.sai.jetpack.repository.RepositoryMain
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repository: RepositoryMain) : ViewModel() {

    //postBanner
    private val banner = MutableLiveData<String>()

    var resultBanner = Transformations.switchMap(banner) {
        liveData {
            val res = try {
                val data = repository.requestDiscovery()
                Result.success(data.data)
            } catch (e: Exception) {
                Result.failure<List<ResBannerItem>>(e)
            }
            emit(res)
        }

    }

    fun onRefreshBanner() {
        banner.value = "start"
    }


    //方式2
    val aa = MutableLiveData<List<ResBannerItem>>()
    fun dd() {
        viewModelScope.launch {
//            val data = repository.requestDiscovery()
//            aa.value = data

            val banner1 = repository.getBanner()


//            if (banner1 is NetResult.Success) {
//                aa.postValue(banner1.data)
//            }else{
//                if (banner1 is NetResult.Error) {
//                    banner1.exception.msg
//                }
//                Log.e("TAG", "dd: ${banner1.exception.msg}" )
//            }

            //简化
            when (banner1) {
                is NetResult.Success  -> { aa.postValue(banner1.data) }
                is NetResult.Error  -> { banner1.exception.msg}
            }
        }
    }
    //方式3
    val bann = liveData {
        kotlin.runCatching {
            val data = repository.requestDiscovery()
            emit(data)
        }
    }

}