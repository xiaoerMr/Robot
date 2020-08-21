package com.sai.jetpack.utils

import android.util.Log
import com.sai.jetpack.BuildConfig

class LogUtils {
    companion object {
        fun e(msg: String) {
            if (BuildConfig.DEBUG) {
               Log.e("--日志--","-> $msg")
            }
        }
    }
}