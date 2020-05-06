package com.juyao.jmvvmdemo.net

import com.juyao.jmvvmdemo.bean.Banner
import retrofit2.http.GET

interface NetService {
    @GET("v2/banners")
    suspend fun getBannerData():HttpResult<List<Banner>>
}