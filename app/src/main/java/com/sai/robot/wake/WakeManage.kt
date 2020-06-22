package com.sai.robot.wake

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.iflytek.cloud.*
import com.iflytek.cloud.util.ResourceUtil
import com.sai.robot.R
import org.json.JSONException
import org.json.JSONObject

/**
 * 唤醒管理
 */
class WakeManage(context: Context) {

    var curThresh = 1450
    var ivwNetMode = "0"
    var keepAlive = "1"
    var ivmSst = "wakeup"
    var audioPath = "${Environment.getExternalStorageDirectory().path}/msc/ivw.wav"
    private val context:Context =context
    lateinit var _Wakeuper: VoiceWakeuper
    val TAG = "-WakeManage-"


    fun onStartWake() {
        _Wakeuper = VoiceWakeuper.getWakeuper()

        //清空参数
        _Wakeuper.setParameter(SpeechConstant.PARAMS, null)
        //唤醒门限值
        _Wakeuper.setParameter(SpeechConstant.IVW_THRESHOLD, "0${curThresh}")
        //唤醒模式
        _Wakeuper.setParameter(SpeechConstant.IVW_SST, ivmSst)
        //设置持续进行唤醒
        _Wakeuper.setParameter(SpeechConstant.KEEP_ALIVE, keepAlive)
        //设置闭环优化网络模式
        _Wakeuper.setParameter(SpeechConstant.IVW_NET_MODE, ivwNetMode)
        //设置唤醒资源路径
        _Wakeuper.setParameter(SpeechConstant.IVW_RES_PATH, getResource())
        //设置唤醒录音保存路径，保存最近一分钟的音频
        _Wakeuper.setParameter(SpeechConstant.IVW_AUDIO_PATH, audioPath)
        _Wakeuper.setParameter(SpeechConstant.AUDIO_FORMAT, "wav")

        _Wakeuper.startListening(object :WakeuperListener{

            override fun onResult(result: WakeuperResult?) {
                Log.d(TAG,"唤醒返回结果")
                if (result != null) {
                    try {
                        val res = JSONObject(result.resultString)
                        val buffer = StringBuffer()
                        buffer.append("[RAW]: ${result.resultString}\n")
                        buffer.append("[操作类型]: ${res.optString("sst")}\n")
                        buffer.append("[唤醒词id]: ${res.optString("id")}\n")
                        buffer.append("[得分]: ${res.optString("score")}\n")
                        buffer.append("[前端点]: ${res.optString("bos")}\n")
                        buffer.append("[尾端点]: ${res.optString("eos")}")
                        Log.d(TAG, buffer.toString())
                    }catch (err: JSONException){
                        Log.d(TAG,"结果解析失败=${err.message}")
                    }
                }else{
                    Log.d(TAG,"返回结果为空")
                }
            }

            override fun onVolumeChanged(p0: Int) {
                Log.d(TAG,"onVolumeChanged=${p0}")
            }

            override fun onBeginOfSpeech() {
                Log.d(TAG,"onBeginOfSpeech")
            }

            override fun onEvent(eventType: Int, isLast: Int, arg2: Int, bundle: Bundle?) {
                Log.d(TAG,"监听事件=${isLast}--${eventType}")
                if (eventType == SpeechEvent.EVENT_RECORD_DATA) {
                    val audio = bundle!!.getByteArray(SpeechEvent.KEY_EVENT_RECORD_DATA)
                    Log.d(TAG,"监听事件=RECORD_DATA--${audio!!.size}")
                }
            }

            override fun onError(error: SpeechError?) {
                Log.d(TAG,"监听失败=${error!!.message}")
            }
        })
    }
    fun onStop(){
        if (_Wakeuper.isListening) {
            _Wakeuper.stopListening()
        }
    }

    fun onDestroy(){
        if (_Wakeuper.isListening) {
            _Wakeuper.stopListening()
        }
        _Wakeuper.destroy()
    }

    private fun getResource(): String {
        return ResourceUtil.generateResourcePath(context,
            ResourceUtil.RESOURCE_TYPE.assets,
            "ivw${context.getString(R.string.app_id)}.jet")

    }
}