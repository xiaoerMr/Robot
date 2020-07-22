package com.sai.robot.dome.data

import com.sai.robot.http.RequestLocal
import com.sai.robot.http.RequestNetWork

/**
 * 提供数据 - http - sql
 */
class SearchRepository() {
    private val requestNetWork = RequestNetWork()
    private val requestLocal = RequestLocal()

    suspend fun getData(query: String): List<String> {

//        //接口 单独封装网络请求，扩展性
//        requestNetWork.getDiscovery(query)
//
//        //接口 单独封装本地缓存，扩展性
//        requestLocal.cacalData()

        return listOf(
            "$query = 1",
            "$query = 2",
            "$query = 3",
            "$query = 4",
            "$query = 5",
            "$query = 6",
            "$query = 7",
            "$query = 8"
        )
    }
}