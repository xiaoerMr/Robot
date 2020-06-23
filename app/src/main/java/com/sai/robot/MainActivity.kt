package com.sai.robot

import android.os.Bundle
import com.iflytek.cloud.SpeechError
import com.iflytek.cloud.WakeuperResult
import com.sai.robot.wake.MyWakeuperListener
import com.sai.robot.wake.WakeManage
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : BaseActivity() {
    lateinit var mWakeManager:WakeManage
    val bufferMain:StringBuffer= StringBuffer();

    override fun onDestroy() {
        super.onDestroy()
        if (mWakeManager != null) {
            mWakeManager.onDestroy()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vWakeUp.setOnClickListener { startWake()}
    }

    private fun startWake() {
        mWakeManager = WakeManage(this)
        mWakeManager.onStartWake(object : MyWakeuperListener {

            override fun onBeginOfSpeech() {
                bufferMain.setLength(0)
                bufferMain.append("--开始听讲--\n")
                vRecord.setText(bufferMain.toString())
            }

            override fun onResult(result: WakeuperResult?) {
                if (result != null) {
                    try {
                        val res = JSONObject(result.resultString)
                        bufferMain.append("---解析成功---\n")
                        bufferMain.append("[RAW]: ${result.resultString}\n")
                        bufferMain.append("[操作类型]: ${res.optString("sst")}\n")
                        bufferMain.append("[唤醒词id]: ${res.optString("id")}\n")
                        bufferMain.append("[得分]: ${res.optString("score")}\n")
                        bufferMain.append("[前端点]: ${res.optString("bos")}\n")
                        bufferMain.append("[尾端点]: ${res.optString("eos")}\n")
                        bufferMain.append("---解析成功-本次结束--\n\n")
                        vRecord.setText(bufferMain.toString())
                    }catch (err: JSONException){
                        bufferMain.append("---解析失败---\n")
                        vRecord.setText(bufferMain.toString())
                    }
                }else{
                    bufferMain.append("---监听结果失败---\n")
                    vRecord.setText(bufferMain.toString())
                }
            }

            override fun onError(error: SpeechError?) {
                bufferMain.append("--监听失败--\n")
                vRecord.setText(bufferMain.toString())
                bufferMain.setLength(0)
            }
        })
    }
}
