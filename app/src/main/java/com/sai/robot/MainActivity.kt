package com.sai.robot

import android.os.Bundle
import com.iflytek.cloud.RecognizerResult
import com.iflytek.cloud.SpeechError
import com.iflytek.cloud.WakeuperResult
import com.sai.robot.iat.IATManager
import com.sai.robot.iat.MyRecognizerListener
import com.sai.robot.tts.MySynthesizerListener
import com.sai.robot.tts.TTSManager
import com.sai.robot.wake.MyWakeuperListener
import com.sai.robot.wake.WakeManage
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
class MainActivity : BaseActivity() {
    lateinit var mWakeManager:WakeManage
    lateinit var _iat:IATManager
    lateinit var _tts:TTSManager
    val bufferWake:StringBuffer= StringBuffer();
    val bufferIAT:StringBuffer= StringBuffer();
    var isStartIAT = false

    override fun onDestroy() {
        super.onDestroy()
        if (mWakeManager != null) {
            mWakeManager.onDestroy()
        }
        if (_iat != null) {
            _iat.onDestory()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 唤醒
        vWakeUp.setOnClickListener { startWake()}
        //语音听写
        vIAT.setOnClickListener { startIAT() }
        vTTS.setOnClickListener { startTTS("山东飞机上飞机哦噻飞机上的减肥啦是的风景三闾大夫就是东方就死哦东方惊悚地方就是大家发来的科技发达") }

    }
    private fun startTTS(msg:String){
            _tts = TTSManager()
            _tts.builfTTs(this)

        _tts.start(msg,object : MySynthesizerListener {
            override fun onSpeakBegin() {
                vTTS.setText("---开始阅读--")
            }

            override fun onSpeakProgress(percent: Int, beginPos: Int, endPos: Int) {
                super.onSpeakProgress(percent, beginPos, endPos)
                vTTS.setText("---阅读进度--${percent}")
            }


            override fun onCompleted(p0: SpeechError?) {
                vTTS.setText("--结束-开始阅读--")
            }

        })
    }

    private fun startIAT(){
         _iat = IATManager(this)
        _iat.starIAT(object : MyRecognizerListener {

            override fun onResult(results: RecognizerResult?, p1: Boolean) {
                bufferIAT.append("----->>>>\n ${results!!.getResultString()} \n<<<<-----\n")
                vIATRecord.setText(bufferIAT.toString())
            }

            override fun onBeginOfSpeech() {

                bufferIAT.setLength(0)
                bufferIAT.append("--开始听写--\n")
                vIATRecord.setText(bufferIAT.toString())
            }

            override fun onEndOfSpeech() {
                bufferIAT.append("--结束听写--\n\n")
                vIATRecord.setText(bufferIAT.toString())
            }
        })
    }

    private fun startWake() {
        mWakeManager = WakeManage(this)
        mWakeManager.onStartWake(object : MyWakeuperListener {

            override fun onBeginOfSpeech() {
                bufferWake.setLength(0)
                bufferWake.append("--开始听讲--\n")
                vRecord.setText(bufferWake.toString())
            }

            override fun onResult(result: WakeuperResult?) {
                if (result != null) {
                    try {
                        val res = JSONObject(result.resultString)
                        bufferWake.append("---解析成功---\n")
                        bufferWake.append("[RAW]: ${result.resultString}\n")
                        bufferWake.append("[操作类型]: ${res.optString("sst")}\n")
                        bufferWake.append("[唤醒词id]: ${res.optString("id")}\n")
                        bufferWake.append("[得分]: ${res.optString("score")}\n")
                        bufferWake.append("[前端点]: ${res.optString("bos")}\n")
                        bufferWake.append("[尾端点]: ${res.optString("eos")}\n")
                        bufferWake.append("---解析成功-本次结束--\n\n")
                        vRecord.setText(bufferWake.toString())
                    }catch (err: JSONException){
                        bufferWake.append("---解析失败---\n")
                        vRecord.setText(bufferWake.toString())
                    }
                }else{
                    bufferWake.append("---监听结果失败---\n")
                    vRecord.setText(bufferWake.toString())
                }
            }

            override fun onError(error: SpeechError?) {
                bufferWake.append("--监听失败--\n")
                vRecord.setText(bufferWake.toString())
                bufferWake.setLength(0)
            }
        })
    }
}


