package com.sai.jetpack

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JetpackApp :Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            this.androidContext(this@JetpackApp)//koin获取context
//
//        }
    }
}