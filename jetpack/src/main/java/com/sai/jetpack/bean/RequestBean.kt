package com.sai.jetpack.bean


data class RequestBean< T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)