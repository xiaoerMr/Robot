package com.sai.robot

import android.app.Application
import com.iflytek.cloud.SpeechConstant
import com.iflytek.cloud.SpeechUtility

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val appId = "=5ef01877"
//      val appId = "=你自己的讯飞Id"

        SpeechUtility.createUtility(this, SpeechConstant.APPID + appId);

    }
}