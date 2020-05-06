package com.juyao.jmvvm.ext

import android.view.View


var start=0L
fun View.setOnSafeClickLitener(action:()->Unit){
    val time=System.currentTimeMillis()
    setOnClickListener {
        if(time-start>500){
            action.invoke()
        }
        start=System.currentTimeMillis()
    }
}

