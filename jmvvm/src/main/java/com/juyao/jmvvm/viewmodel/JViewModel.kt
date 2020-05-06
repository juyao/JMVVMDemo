package com.juyao.jmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.NullPointerException

open class JViewModel : ViewModel() {
    open fun <T> apiRequest(
        request: suspend () -> T?,
        onSuccess: (T) -> Unit = {},
        onFail: (Throwable) -> Unit = {}
    ): Job {
        return viewModelScope.launch(Dispatchers.Main) {
            try {
                val res: T? = withContext(Dispatchers.IO) { request() }
                if (null != res) {
                    onSuccess(res)
                } else {
                    onFail(NullPointerException())
                }
            } catch (e: Exception) {
                onFail(e)
            }
        }
    }

}