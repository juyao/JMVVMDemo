package com.juyao.jmvvmdemo.net

import com.juyao.jmvvm.net.JApi

class Api {
    companion object{
        val API_BASE_URL="https://gank.io/api/"
        fun getNetService():NetService{
            var netService:NetService?=null
            if(null==netService){
                synchronized(Api::class.java){
                    netService = JApi.getRetrofit(API_BASE_URL).create(NetService::class.java)
                }
            }
            return netService!!
        }
    }
}