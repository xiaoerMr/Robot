package com.sai.robot.robot.tts

import android.os.Bundle
import com.iflytek.cloud.SynthesizerListener

interface MySynthesizerListener: SynthesizerListener {
    override fun onBufferProgress(p0: Int, p1: Int, p2: Int, p3: String?) {
    }

    override fun onSpeakProgress(p0: Int, p1: Int, p2: Int) {
    }

    override fun onSpeakPaused() {
    }

    override fun onSpeakResumed() {
    }

    override fun onEvent(p0: Int, p1: Int, p2: Int, p3: Bundle?) {
    }


}