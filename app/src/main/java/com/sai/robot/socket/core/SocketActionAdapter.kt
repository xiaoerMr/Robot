package com.sai.robot.socket.core

abstract class SocketActionAdapter : ISocketActionListener {
    override fun onSocketReadResponse(read: String) {
    }

    override fun onSocketWriteResponse(write: String) {
    }

    override fun onPulseSend() {
    }

    override fun onSocketDisconnection(error: String) {
    }

    override fun onSocketConnectionSuccess() {
    }

    override fun onSocketConnectionFailed(error: String) {
    }
}