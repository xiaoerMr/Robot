package com.sai.robot.corouties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.sai.robot.R
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class CoroutinesActivity : AppCompatActivity() {

    private val buffer = StringBuffer();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        supportActionBar?.title = "协程"

//        CoroutineScope()
//        SequenceScope

//        test()
//        testGetData()
//        GlobalScope.launch {
//            vContext.text = testGetData2()
//        }
//        fun4()//flow
    }

    private fun fun4() {
        val launch = lifecycleScope.launch(Dispatchers.IO) {
            flow<Int> {
                for (i in 1..3) {
                    emit(i)
                }
                println("name = ${Thread.currentThread().name}")
            }
                .flowOn(Dispatchers.Unconfined)//IO线程的协程中
                .filter {
                    println("--过滤--name = ${Thread.currentThread().name}")
                    it % 2 == 1
                }
                .map {
                    println("--转换--name = ${Thread.currentThread().name}")
                    " -->  $it "
                }//转换
                .catch { e -> println("catch = ${e.message}") }
                .onCompletion { println("--flow 完成 --name = ${Thread.currentThread().name}") }
                .onStart { println("--flow 开始 --name = ${Thread.currentThread().name}") }
                .onEmpty { println("--flow 空 --name = ${Thread.currentThread().name}") }
                .collect {
                    println("$it name = ${Thread.currentThread().name}")
                }
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

    private suspend fun testGetData2(): String {
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