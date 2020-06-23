package com.sai.robot.iat

import android.content.Context
import android.util.Log
import com.iflytek.cloud.InitListener
import com.iflytek.cloud.RecognizerListener
import com.iflytek.cloud.SpeechConstant
import com.iflytek.cloud.SpeechRecognizer

class IATManager(context: Context) {
val TAG = "-IATManager-"
    var mEngineType = SpeechConstant.TYPE_CLOUD

    val context = context
    var   _mIat :SpeechRecognizer ? = null

    fun starIAT(listener: RecognizerListener){
       _mIat = SpeechRecognizer.createRecognizer(context, InitListener(){
          Log.d(TAG,"语音识别器-初始化=${it}")
      })
        //设置语法ID和 SUBJECT 为空，以免因之前有语法调用而设置了此参数；
        // 或直接清空所有参数，具体可参考 DEMO 的示例。
        _mIat!!.setParameter( SpeechConstant.CLOUD_GRAMMAR, null )
        _mIat!!.setParameter( SpeechConstant.SUBJECT, null )
//设置返回结果格式，目前支持json,xml以及plain 三种格式，其中plain为纯听写文本内容
        _mIat!!.setParameter(SpeechConstant.RESULT_TYPE, "plain")
//此处engineType为“cloud”
        _mIat!!.setParameter( SpeechConstant.ENGINE_TYPE, mEngineType )
//设置语音输入语言，zh_cn为简体中文
        _mIat!!.setParameter(SpeechConstant.LANGUAGE, "zh_cn")
//设置结果返回语言
        _mIat!!.setParameter(SpeechConstant.ACCENT, "mandarin")
// 设置语音前端点:静音超时时间，单位ms，即用户多长时间不说话则当做超时处理
//取值范围{1000～10000}
        _mIat!!.setParameter(SpeechConstant.VAD_BOS, "4000")
//设置语音后端点:后端点静音检测时间，单位ms，即用户停止说话多长时间内即认为不再输入，
//自动停止录音，范围{0~10000}
        _mIat!!.setParameter(SpeechConstant.VAD_EOS, "1000")
//设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        _mIat!!.setParameter(SpeechConstant.ASR_PTT,"1")

        //开始识别，并设置监听器
        _mIat!!.startListening(listener);
    }


    fun onStop(){
        if (_mIat == null) {
            return
        }
        if (_mIat!!.isListening) {
            _mIat!!.stopListening()
        }
    }

    fun onDestory(){
        if (_mIat == null) {
            return
        }
        if (_mIat!!.isListening) {
            _mIat!!.stopListening()
        }
        _mIat!!.destroy()
    }
}