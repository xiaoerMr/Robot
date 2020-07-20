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
        return listOf("1", "2", "3", "4", "5", "6", "7", "8")
    }
}