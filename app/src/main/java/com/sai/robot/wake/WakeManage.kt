package com.sai.robot.wake

import android.content.Context
import android.os.Environment
import android.util.Log
import com.iflytek.cloud.*
import com.iflytek.cloud.util.ResourceUtil
import com.sai.robot.R
import java.io.File

/**
 * 唤醒管理
 */
class WakeManage(context: Context) {

    var curThresh = 1450
    var ivwNetMode = "1"
    var keepAlive = "1"
    var ivmSst = "wakeup"
    var audioPath =
        "${context.getExternalFilesDir(Environment.DIRECTORY_MOVIES)!!.absolutePath}${File.separator}"
    private val context: Context = context
    private var wakeuper: VoiceWakeuper? = null
    val TAG = "-WakeManage-"


    fun onStartWake(listener: WakeuperListener) {
        wakeuper?.let {
            it.startListening(listener)
        }
    }

    private fun buildWakeuper() {
        // 初始化唤醒对象
        wakeuper = VoiceWakeuper.createWakeuper(context, null)

        wakeuper?.let {
            wakeuper = VoiceWakeuper.getWakeuper()

            //清空参数
            it.setParameter(SpeechConstant.PARAMS, null)
            //唤醒门限值
            it.setParameter(SpeechConstant.IVW_THRESHOLD, "0${curThresh}")
            //唤醒模式
            it.setParameter(SpeechConstant.IVW_SST, ivmSst)
            //设置持续进行唤醒
            it.setParameter(SpeechConstant.KEEP_ALIVE, keepAlive)
            //设置闭环优化网络模式 0：关闭
            it.setParameter(SpeechConstant.IVW_NET_MODE, ivwNetMode)
            //设置唤醒资源路径
            it.setParameter(SpeechConstant.IVW_RES_PATH, getResource())
            //设置唤醒录音保存路径，保存最近一分钟的音频  /msc/ivw.wav
            val file = File("${audioPath}msc${File.separator}")
            if (!file.exists()) {
                file.mkdirs()
            }
            Log.d(TAG, "录音保存路径=${file.absolutePath}")
            it.setParameter(SpeechConstant.IVW_AUDIO_PATH, "${audioPath}ivw.wav")
            it.setParameter(SpeechConstant.AUDIO_FORMAT, "wav")

            //发音人 xiaoyan aisxping aisjinger
            it.setParameter(SpeechConstant.VOICE_NAME, "aisjinger")
            //在线模式 TYPE_CLOUD 在线语音； TYPE_LOCAL  本地模式
            it.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD)

        }
//
    }

    fun onStop() {
        wakeuper?.let {
            if (it.isListening) {
                it.stopListening()
            }
        }
    }

    fun onDestroy() {
        wakeuper?.let {
            if (it.isListening) {
                it.stopListening()
            }
            it.destroy()
        }
    }

    private fun getResource(): String {
        val result = ResourceUtil.generateResourcePath(
            context,
            ResourceUtil.RESOURCE_TYPE.assets,
            "ivw/${context.getString(R.string.app_id)}.jet"
        )
        return result
    }
}