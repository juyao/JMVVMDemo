package com.juyao.jmvvmdemo.viewmodel

import android.widget.Toast
import com.juyao.jmvvm.viewmodel.JViewModel
import kotlinx.coroutines.Job

open class BaseViewModel: JViewModel() {
    private val myOnFail: (Throwable) -> Unit={

    }
    fun <T> apiRequest(
        request: suspend () -> T?,
        onSuccess: (T) -> Unit
    ): Job {
        return super.apiRequest(request, onSuccess, myOnFail)
    }
}