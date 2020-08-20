package com.sai.jetpack.http

import com.sai.jetpack.UrlBanner
import com.sai.jetpack.bean.RequestBean
import com.sai.jetpack.bean.ResBannerItem
import retrofit2.http.GET

interface ApiService {

    @GET(UrlBanner)
    suspend fun getBannerData(): RequestBean<List<ResBannerItem>>


}