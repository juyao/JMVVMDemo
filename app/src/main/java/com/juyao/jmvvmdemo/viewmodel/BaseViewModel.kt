package com.juyao.jmvvmdemo.viewmodel

import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.juyao.jmvvm.viewmodel.JViewModel
import com.juyao.jmvvmdemo.net.HttpResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.NullPointerException

open class BaseViewModel: JViewModel() {
    private val myOnFail: (Throwable) -> Unit={

    }
    fun <T> apiRequest(
        request: suspend () -> HttpResult<T>?,
        onSuccess: (T?) -> Unit,
        onFail:(Throwable) -> Unit=myOnFail
    ): Job {
        return viewModelScope.launch(Dispatchers.Main) {
            try {
                val res: HttpResult<T>? = withContext(Dispatchers.IO) { request() }
                if (null != res) {
                    if(res.status==100){
                        onSuccess(res.data)
                    }else{
                        onFail(Exception("状态值异常"))
                    }
                } else {
                    onFail(Exception("返回值为空"))
                }
            } catch (e: Exception) {
                onFail(e)
            }
        }
    }
}