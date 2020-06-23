package com.sai.robot.tts

import android.content.Context
import android.os.Environment
import android.util.Log
import com.iflytek.cloud.InitListener
import com.iflytek.cloud.SpeechConstant
import com.iflytek.cloud.SpeechSynthesizer
import com.iflytek.cloud.SynthesizerListener
import java.io.File

class TTSManager {
    var TAG ="-TTSManager-"
var  _mTts:SpeechSynthesizer?=null

    fun  builfTTs(context: Context){
        _mTts = SpeechSynthesizer.createSynthesizer(context, InitListener{
            Log.d(TAG,"文字阅读器-初始化=${it}")
        })

        var Path = "${context.getExternalFilesDir(Environment.DIRECTORY_MOVIES)!!.absolutePath}${File.separator}"

        // 清空参数
        _mTts!!.setParameter(SpeechConstant.PARAMS, null)
        _mTts!!.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD)
        //支持实时音频返回，仅在synthesizeToUri条件下支持
        _mTts!!.setParameter(SpeechConstant.TTS_DATA_NOTIFY, "1")
        //设置在线合成发音人 xiaoyan aisxping aisjinger
        _mTts!!.setParameter(SpeechConstant.VOICE_NAME, "aisjinger")
        //设置合成语速
        _mTts!!.setParameter(SpeechConstant.SPEED, "50")
        //设置合成音调
        _mTts!!.setParameter(SpeechConstant.PITCH, "50")
        //设置合成音量
        _mTts!!.setParameter(SpeechConstant.VOLUME,"50")
        //设置播放器音频流类型
        _mTts!!.setParameter(SpeechConstant.STREAM_TYPE, "3")
        // 设置播放合成音频打断音乐播放，默认为true
        _mTts!!.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "false");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        _mTts!!.setParameter(SpeechConstant.AUDIO_FORMAT, "pcm");
        _mTts!!.setParameter(SpeechConstant.TTS_AUDIO_PATH, "${Path}/msc/tts.pcm");

    }
    fun start(msg:String,listener : SynthesizerListener){
        _mTts!!.startSpeaking(msg, listener);
    }

    fun stop (){
        if (_mTts == null) {
            return
        }
        if (_mTts!!.isSpeaking) {
            _mTts!!.stopSpeaking()
        }
    }
    fun onDestroy(){
        if (_mTts == null) {
            return
        }
        if (_mTts!!.isSpeaking) {
            _mTts!!.stopSpeaking()
        }
        _mTts!!.destroy()
    }
}