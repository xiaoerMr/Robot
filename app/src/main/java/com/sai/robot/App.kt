package com.sai.robot

import android.app.Application
import com.iflytek.cloud.SpeechConstant
import com.iflytek.cloud.SpeechUtility

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        SpeechUtility.createUtility(this, "${SpeechConstant.APPID}=5ef01877");

    }
}