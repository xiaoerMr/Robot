package com.sai.robot.socket

import com.xuhao.didi.core.iocore.interfaces.ISendable

class SendMsgData(private val msg:String) : ISendable {

    override fun parse(): ByteArray {
        return  msg.toByteArray()
    }
}