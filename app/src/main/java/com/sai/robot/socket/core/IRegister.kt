package com.sai.robot.socket.core

interface IRegister <T, E>{

    /**
     * 注册一个回调接收器
     *
     * @param socketActionListener 回调接收器
     */
    fun registerReceiver(socketActionListener: T?): E?

    /**
     * 解除回调接收器
     *
     * @param socketActionListener 注册时的接收器,需要解除的接收器
     */
    fun unRegisterReceiver(socketActionListener: T?): E?
}