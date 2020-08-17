package com.sai.robot.page.data.local

//import androidx.paging.PagingSource
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query

//@Dao
interface TestDao {

//    @Query("select * from TestEntity")
//    fun queryAllData():PagingSource<Int,TestEntity>
//    @Query("select * from TestEntity")
    fun queryAllData():List<TestEntity>

//    @Insert
    fun insertItem(item:List<TestEntity>)
}