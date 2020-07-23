package com.sai.robot.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.WorkManager
import java.lang.ref.WeakReference

open class BaseActivity : AppCompatActivity() {
    // 是否是前台
    protected var isActive = false

    // 弱引用
    private var activityWR :WeakReference<AppCompatActivity> ? = null

    protected val TAG = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityWR = WeakReference(this)

        ActivityCollector.pushTask(activityWR!!)

//        WorkManager.getInstance(this).getWorkInfoByIdLiveData()
    }

    override fun onResume() {
        super.onResume()
        isActive =true
    }

    override fun onPause() {
        super.onPause()
        isActive =false
    }
    override fun onDestroy() {
        super.onDestroy()
        activityWR?.let {
            ActivityCollector.removeTask(it)
        }
    }
}