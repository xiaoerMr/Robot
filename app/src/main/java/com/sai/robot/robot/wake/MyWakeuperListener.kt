package com.sai.robot.robot.wake

import android.os.Bundle
import com.iflytek.cloud.SpeechError
import com.iflytek.cloud.WakeuperListener

interface MyWakeuperListener: WakeuperListener {


    override fun onVolumeChanged(p0: Int) {
    }


    override fun onError(p0: SpeechError?) {
    }

    override fun onEvent(p0: Int, p1: Int, p2: Int, p3: Bundle?) {
    }
}