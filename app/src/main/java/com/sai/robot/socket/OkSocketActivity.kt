package com.sai.robot.socket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sai.robot.R
import com.xuhao.didi.core.iocore.interfaces.IPulseSendable
import com.xuhao.didi.core.iocore.interfaces.ISendable
import com.xuhao.didi.core.pojo.OriginalData
import com.xuhao.didi.core.protocol.IReaderProtocol
import com.xuhao.didi.socket.client.sdk.OkSocket
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
import com.xuhao.didi.socket.client.sdk.client.connection.NoneReconnect
import kotlinx.android.synthetic.main.activity_socket.*
import java.lang.Exception
import java.nio.ByteOrder
import java.nio.charset.Charset

class OkSocketActivity : AppCompatActivity() {

    companion object {
        fun actionStart(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, OkSocketActivity::class.java))
        }
    }

    val mBuffer = StringBuffer()
    var manager: IConnectionManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket)

        vStart.setOnClickListener {
            manager = initSocket()
            manager!!.connect()
        }

        //发送消息
        vSend.setOnClickListener {
            val msg = vInput.text.toString()
            manager?.let {
                if (it.isConnect) {
                    it.send(SendMsgData(msg))
                    setOutput(msg)
                }else{
                    setOutput("--连接已断开")
                }
            }
        }
    }

    private fun initSocket(): IConnectionManager {
        val info = ConnectionInfo("192.168.1.60", 8080)

        val options = OkSocketOptions.Builder()
            .setReconnectionManager(NoneReconnect())
            .setConnectTimeoutSecond(10)
            .setReaderProtocol(object :IReaderProtocol{
                override fun getHeaderLength(): Int {
                    return 10 // 16777216
                }

                override fun getBodyLength(header: ByteArray?, byteOrder: ByteOrder?): Int {
                    if (header == null) { return 0 }
                    else{ return header.size }
                }
            })
            .build()

        val manager = OkSocket.open(info)//.option(options)
        manager.registerReceiver(adapter)
        return manager
    }

    private fun setOutput(msg: String) {
        runOnUiThread {
            val toString = mBuffer.append(msg).append("\n\t").toString()
            vOutput.text = toString
        }
    }


    val adapter = object : SocketActionAdapter() {

        override fun onSocketConnectionSuccess(info: ConnectionInfo?, action: String?) {
            setOutput("-- 连接成功")
        }

        override fun onSocketDisconnection(
            info: ConnectionInfo?,
            action: String?,
            e: Exception?
        ) {
            if (e != null) {
                setOutput("---失去连接=> ${e.message}")
                Log.e("TAG", "失去连接message: ${e.message}" )
            } else setOutput("---正常失去连接")
        }

        override fun onSocketReadResponse(
            info: ConnectionInfo?,
            action: String?,
            data: OriginalData?
        ) {
            // 接收消息回调
            if (data != null) {
                setOutput("--接收到消息：${ String(data.bodyBytes, Charset.defaultCharset())}")
            }else{
                setOutput("--等待接收到消息")
            }
        }

        override fun onSocketWriteResponse(
            info: ConnectionInfo?,
            action: String?,
            data: ISendable?
        ) {
            //发送消息回调
//            data?.let {
//                val bodyBytes = String(it.parse(), Charset.defaultCharset())
//                setOutput("--Write发送消息：${bodyBytes}")
//            }
        }

        override fun onPulseSend(info: ConnectionInfo?, data: IPulseSendable?) {
            //心跳发送回调
            data?.let {
                val bodyBytes = String(it.parse(), Charset.defaultCharset())
                setOutput("--心跳发送消息：${bodyBytes}")
            }
        }

        override fun onSocketConnectionFailed(
            info: ConnectionInfo?,
            action: String?,
            e: Exception?
        ) {
            if (e != null) setOutput("---连接失败=> ${e.message}")
            else setOutput("---连接失败${action}")
        }
    }
}