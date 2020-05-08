package com.juyao.jmvvmdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juyao.jmvvmdemo.bean.Banner
import com.juyao.jmvvmdemo.bean.Categories
import com.juyao.jmvvmdemo.net.Api

class MainViewModel : BaseViewModel() {
    val bannerData:MutableLiveData<List<Banner>> by lazy {
        MutableLiveData<List<Banner>>()
    }
    val categoriesData:MutableLiveData<List<Categories>> by lazy {
        MutableLiveData<List<Categories>>()
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
    fun getCategories(){
        apiRequest(
            {
              Api.getNetService().getCategories()
            },{
                categoriesData.value=it
            }
        )
    }
}