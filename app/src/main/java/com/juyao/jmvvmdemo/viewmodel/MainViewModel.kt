package com.juyao.jmvvmdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juyao.jmvvmdemo.bean.Banner
import com.juyao.jmvvmdemo.net.Api

class MainViewModel : BaseViewModel() {
    val bannerData:MutableLiveData<List<Banner>> by lazy {
        MutableLiveData<List<Banner>>()
    }
    fun getBanner() {
        apiRequest(
            {
                Api.getNetService().getBannerData()
            },
            {
                Log.i("MainViewModel",it.toString())
                bannerData.value=it
            }

        )
    }
}