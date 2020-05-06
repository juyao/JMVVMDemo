package com.juyao.jmvvmdemo.viewmodel

import android.util.Log
import com.juyao.jmvvmdemo.net.Api

class MainViewModel : BaseViewModel() {
    fun getBanner() {
        apiRequest(
            {
                Api.getNetService().getBannerData()
            },
            {
                Log.i("MainViewModel", "onsuccess:$it")
            }

        )
    }
}