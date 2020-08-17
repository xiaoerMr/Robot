package com.sai.robot.page.data.repostory

//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.PagingData
//import androidx.paging.PagingSource
import com.sai.robot.page.bean.TestBean
import com.sai.robot.page.data.local.AppDataBase
import com.sai.robot.page.data.local.TestEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * 数据源实现类
 */
class TestRepositoryImpl(val appDataBase: AppDataBase):Repository {

    private val mTestDao by lazy {
        appDataBase.testDao()
    }

    override fun postOfData() {
    }

    //    override fun postOfData(): Flow<PagingData<TestBean>> = flow {  }
    //    override fun postOfData() : Flow<PagingData<TestBean>> {
//        return Pager(pagingConfig){
//
//            //加载数据库数据
////            mTestDao.queryAllData()
//
//        }.flow.map{
//
//            it.map {
//                TestBean(it.name)
//            }
//        }
//    }
    override fun insertOfData(list:List<TestEntity>){
        mTestDao.insertItem(list)
    }
}