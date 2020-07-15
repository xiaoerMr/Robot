package com.sai.robot.corouties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sai.robot.R
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {

    private val buffer = StringBuffer();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        supportActionBar?.title = "协程"


//        test()
//        testGetData()
        GlobalScope.launch {
            vContext.text = testGetData2()
        }
    }

    private fun testGetData() {
        // 异步 launch 无返回值
        buffer.setLength(0)
        buffer.append("-------》异步 launch 耗时 1秒\n")
        buffer.append("--开始-\n")
        GlobalScope.launch() {
            val name = doGetData("3")
            buffer.append("--完成请求 =${name}\n")
            vContext.text = buffer.toString()
        }
        buffer.append("--结束-\n")
    }

    private suspend fun testGetData2() :String{
        // 异步 async 无返回值
        buffer.append("--完成请求 1=\n")
        val name = GlobalScope.async {
            val name = doGetData("4")
            buffer.append("--完成请求 =${name}\n")
            buffer.toString()
        }
        buffer.append("--完成请求 2=\n")
        return name.await()
    }

    suspend fun doGetData(name: String): String {
        delay(1000)
        return "success->${name}"
    }

    private fun test() {
        //循环20次
        repeat(20) {
            //开启协程
            //            GlobalScope.launch(Dispatchers.Unconfined) {
            GlobalScope.launch() {
                Log.d(
                    "---",
                    "Thread-name = ${Thread.currentThread().name}  , id = ${Thread.currentThread().id}"
                )
                //延迟 1秒
                delay(1000)
            }
        }
    }


    companion object {
        fun actionStart(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, CoroutinesActivity::class.java))
        }
    }
}