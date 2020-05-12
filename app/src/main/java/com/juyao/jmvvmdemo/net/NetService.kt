package com.juyao.jmvvmdemo.net

import com.juyao.jmvvmdemo.bean.Article
import com.juyao.jmvvmdemo.bean.Banner
import com.juyao.jmvvmdemo.bean.Categories
import retrofit2.http.GET
import retrofit2.http.Path

interface NetService {
    @GET("v2/banners")
    suspend fun getBannerData():HttpResult<List<Banner>>
    @GET("v2/categories/Article")
    suspend fun getCategories():HttpResult<List<Categories>>
    @GET("v2/data/category/GanHuo/type/{type}/page/{page}/count/50")
    suspend fun getArticles(@Path("type")type:String,@Path("page")page:Int):HttpResult<List<Article>>

}