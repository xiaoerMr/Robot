package com.sai.robot.socket.core

interface ISocketActionListener {
//    fun onSocketIOThreadStart(s: String)

//    fun onSocketIOThreadShutdown(s: String, e: Exception)

    fun onSocketReadResponse(read: String)

    fun onSocketWriteResponse(write: String)

    fun onPulseSend()

    fun onSocketDisconnection(error:String)

    fun onSocketConnectionSuccess()

    fun onSocketConnectionFailed(error:String)
}