package com.sai.robot.socket

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sai.robot.R
import kotlinx.android.synthetic.main.activity_socket.*
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class SocketActivity : AppCompatActivity() {
// https://github.com/jiusetian/EasySocket/blob/master/socker_server/src/main/java/com/socker_server/iowork/EasyReader.java
// https://github.com/jarryleo/MagicMessenger
    companion object {
        fun actionStart(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, SocketActivity::class.java))
        }
    }

    val mBuffer = StringBuffer()

    // 初始化线程池
    val mThreadPool by lazy { Executors.newScheduledThreadPool(4) }
    var mSocket: Socket? = null
    var inputStream: InputStream? = null
    var outputStream: OutputStream? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket)

        //连接
        vStart.setOnClickListener {
            mThreadPool.execute {
                goStart()
            }
        }

        //发送消息
        vSend.setOnClickListener {
            val msg = vInput.text.toString()

            mThreadPool.execute {
                mSocket?.let {
                    sendMsg(it, msg)
                }
            }
        }

        //重连
        vReStart.setOnClickListener {
            mThreadPool.execute {
                setOutput("-----重新连接")
                goStart()
            }
        }

        //关闭
        vOver.setOnClickListener {
            mSocket?.let {

                outputStream?.let {
                    it.close()
                }

                inputStream?.let {
                    it.close()
                }

                it.close()

                setOutput("------结束")
            }
        }

    }


    //接收
    private fun onReceive() {
        //延后2秒后，每隔1秒获取一次消息
        mThreadPool.scheduleAtFixedRate(
            {
                mSocket?.let {
                    if (it.isConnected) {
                        inputStream?.let {

                            var byteArray = ByteArray(1024)

                            var len = it.read(byteArray)

                            if (len > 0) {
                                var text = String(byteArray, 0, len)
                                setOutput("-接收消息: $text")
                            } else {
                                setOutput("--等待接收消息--")
                            }
                        }
                    } else {
                        // 断开
                        setOutput("--已经断开连接，立刻重连--")
                        goStart()
                    }
                }
            },
            2 * 1000,
            1 * 1000,
            TimeUnit.MILLISECONDS
        )
    }

    //连接
    private fun goStart() {
        mSocket = Socket("192.168.1.83", 8080)
        setOutput("--连接是否成功： ${mSocket!!.isConnected}")


        //发送数据
        mSocket?.let {
            outputStream = it.getOutputStream()
//            sendMsg(it, "{\"securitynumber\":\"999999002\"}")
            inputStream = it.getInputStream()
        }

        // 启动接收
        runOnUiThread {
            onReceive()
        }
    }

    private fun sendMsg(it: Socket, msg: String) {
        mSocket?.let {
            if (it.isConnected) {
                outputStream?.let {
                    it.write(msg.toByteArray())
                    it.flush()
                    setOutput("已发送消息： ${msg}")
                }
            }else{
                setOutput("--已经断开连接，立刻重连--并发送消息")
                goStart()
                sendMsg(it,msg)
            }
        }
    }

    private fun setOutput(msg: String) {
        runOnUiThread {
            val toString = mBuffer.append(msg).append("\n\t").toString()
            vOutput.text = toString
        }
    }
}