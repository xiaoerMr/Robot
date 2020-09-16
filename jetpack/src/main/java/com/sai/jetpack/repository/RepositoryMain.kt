package com.sai.jetpack.repository

import android.net.ParseException
import com.google.gson.JsonParseException
import com.sai.jetpack.bean.RequestBean
import com.sai.jetpack.bean.ResBannerItem
import com.sai.jetpack.http.HttpManagerImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.json.JSONException
import java.lang.Error
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import javax.net.ssl.SSLHandshakeException

class RepositoryMain {

     suspend fun requestDiscovery() = withContext(Dispatchers.IO) {
        val response = HttpManagerImpl().service.getBannerData()
        response
    }

    //方式2
    // https://github.com/winlee28/Jetpack-WanAndroid/blob/master/lib_net/src/main/java/com/win/lib_net/net/BaseRepository.kt
    suspend fun getBanner(): NetResult<List<ResBannerItem>> {
        return callRequest(call = { requestBanner() })
    }

    private suspend fun requestBanner() =
        handleResponse(HttpManagerImpl().service.getBannerData())



    suspend fun <T : Any> callRequest(call: suspend () -> NetResult<T>): NetResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            //这里统一处理异常
            e.printStackTrace()
//            NetResult.Error(DealException.handlerException(e))
            NetResult.Error(ResultException("","统一处理异常"))
        }
    }

    suspend fun <T : Any> handleResponse(
        response: RequestBean<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): NetResult<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                NetResult.Error(
                    ResultException(
                        response.errorCode.toString(),
                        response.errorMsg
                    )
                )
            } else {
                successBlock?.let { it() }
                NetResult.Success(response.data)
            }
        }
    }
}

sealed class NetResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetResult<T>()

    data class Error(val exception: ResultException) : NetResult<Nothing>()
//    object NotANumber:NetResult<Nothing>()
}

class ResultException(var errCode: String?, var msg: String?) : Exception(msg)

//object DealException {
//
//    fun handlerException(t: Throwable): ResultException {
//        val ex: ResultException
//        if (t is ResultException) {
//            ex = t
//        } else if (t is HttpException) {
//            ex = when (t.code()) {
//                ApiResultCode.UNAUTHORIZED,
//                ApiResultCode.FORBIDDEN,
//                    //权限错误，需要实现
//                ApiResultCode.NOT_FOUND -> ResultException(
//                    t.code().toString(),
//                    "网络错误"
//                )
//                ApiResultCode.REQUEST_TIMEOUT,
//                ApiResultCode.GATEWAY_TIMEOUT -> ResultException(
//                    t.code().toString(),
//                    "网络连接超时"
//                )
//                ApiResultCode.INTERNAL_SERVER_ERROR,
//                ApiResultCode.BAD_GATEWAY,
//                ApiResultCode.SERVICE_UNAVAILABLE -> ResultException(
//                    t.code().toString(),
//                    "服务器错误"
//                )
//                else -> ResultException(t.code().toString(), "网络错误")
//            }
//        } else if (t is JsonParseException
//            || t is JSONException
//            || t is ParseException
//        ) {
//            ex = ResultException(
//                ApiResultCode.PARSE_ERROR,
//                "解析错误"
//            )
//        } else if (t is SocketException) {
//            ex = ResultException(
//                ApiResultCode.REQUEST_TIMEOUT.toString(),
//                "网络连接错误，请重试"
//            )
//        } else if (t is SocketTimeoutException) {
//            ex = ResultException(
//                ApiResultCode.REQUEST_TIMEOUT.toString(),
//                "网络连接超时"
//            )
//        } else if (t is SSLHandshakeException) {
//            ex = ResultException(
//                ApiResultCode.SSL_ERROR,
//                "证书验证失败"
//            )
//            return ex
//        } else if (t is UnknownHostException) {
//            ex = ResultException(
//                ApiResultCode.UNKNOW_HOST,
//                "网络错误，请切换网络重试"
//            )
//            return ex
//        } else if (t is UnknownServiceException) {
//            ex = ResultException(
//                ApiResultCode.UNKNOW_HOST,
//                "网络错误，请切换网络重试"
//            )
//        } else if (t is NumberFormatException) {
//            ex = ResultException(
//                ApiResultCode.UNKNOW_HOST,
//                "数字格式化异常"
//            )
//        } else {
//            ex = ResultException(
//                ApiResultCode.UNKNOWN,
//                "未知错误"
//            )
//        }
//        return ex
//    }
//}