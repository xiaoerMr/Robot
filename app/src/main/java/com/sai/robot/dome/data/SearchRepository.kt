package com.sai.robot.dome.data

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 提供数据 - http - sql
 */
class SearchRepository {


    fun getData(query:String):List<String>{
//        val launch = GlobalScope.launch {
//
//            listOf("1", "2", "3", "4", "5", "6", "7", "8")
//        }
//
        return listOf("$query = 1", "$query = 2", "$query = 3", "$query = 4", "$query = 5", "$query = 6", "$query = 7", "$query = 8")
    }

    fun getDataFormNet(query:String):List<String>{
        // serviceApi.getSearch(query)
        // success  error
        // 广播获取到了结果，准你们备接受  ConflatedBroadcastChannel.offer(list)
        return listOf()
    }
}