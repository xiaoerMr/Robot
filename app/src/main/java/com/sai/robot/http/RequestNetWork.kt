package com.sai.robot.http

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RequestNetWork {


    // 暂停协程 判断 响应是否为空
    private suspend fun <T> Call<T>.await(): T {
        //暂停协程 suspendCoroutine
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {

                    val body = response.body()

                    if (body != null)  continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }


    // 请求服务
    private val searchService = ServiceCreator.create(SearchService::class.java)


    //  **接口请求
    suspend fun getDiscovery(query: String) = searchService.getDiscovery(query).await()
}