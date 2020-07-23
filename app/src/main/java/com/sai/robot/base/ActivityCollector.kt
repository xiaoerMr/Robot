package com.sai.robot.base

import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference
import java.util.*

/**
 *  管理 Activity
 *
 *  单例模式
 */
object ActivityCollector {
    private val activitys = Stack<WeakReference<AppCompatActivity>>()


    fun pushTask(task: WeakReference<AppCompatActivity>) {
        activitys.push(task)
    }

    fun removeTask(task: WeakReference<AppCompatActivity>) {
        activitys.remove(task)
    }

    fun removeAll() {
        for (reference in activitys) {
            val activity = reference.get()
            activity?.let {
                if (!activity.isFinishing) {
                    activity.finish()
                }
            }
        }
    }
}