package com.sai.robot.dome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.sai.robot.dome.data.SearchRepository

/**
 * 持有仓库 对象，由仓库提供 从http来的数据 和 从sql来到数据
 */
class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    public val queryLiveData = MutableLiveData<List<String>>()

//    //将输入LiveDate转成结果LiveDate
//    val result: LiveData<String> = queryLiveData.switchMap { query -> LiveData  {
//                //  请求网络返回到结果
//                //val ren: Flow=
//            //             emitSource(re）}
//        //             }
//
//    }


    fun getNewData(query: String) {

        val data = repository.getData(query)

        queryLiveData.postValue(data)
    }
}