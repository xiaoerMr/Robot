package com.sai.robot.page.data.repostory

//import androidx.paging.PagingData
import com.sai.robot.page.bean.TestBean
import com.sai.robot.page.data.local.TestEntity
import kotlinx.coroutines.flow.Flow

/**
 * 数据源 获取方式 接口
 */
interface Repository {
    fun postOfData()//: Flow<PagingData<TestBean>>
    fun insertOfData(list:List<TestEntity>)
}