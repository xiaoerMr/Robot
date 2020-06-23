package com.sai.robot.iat

import android.os.Bundle
import com.iflytek.cloud.RecognizerListener
import com.iflytek.cloud.RecognizerResult
import com.iflytek.cloud.SpeechError

interface MyRecognizerListener: RecognizerListener {

    override fun onVolumeChanged(p0: Int, p1: ByteArray?) {
    }


    override fun onError(p0: SpeechError?) {
    }

    override fun onEvent(p0: Int, p1: Int, p2: Int, p3: Bundle?) {
    }
}