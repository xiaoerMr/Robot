package com.sai.robot.page.data.local

import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase

/**
 * 本地数据库
 */

//@Database(entities = arrayOf(TestEntity::class), version = 1, exportSchema = false)
abstract class AppDataBase {//}: RoomDatabase() {

    abstract fun testDao(): TestDao
}

class DBManager(applicationContext: Context){
//    val db = Room.databaseBuilder(
//        applicationContext,
//        AppDataBase::class.java, "database-name"
//    ).allowMainThreadQueries().build()
}