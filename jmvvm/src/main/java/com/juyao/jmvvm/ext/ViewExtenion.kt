package com.juyao.jmvvm.ext

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide



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

fun ImageView.load(url:String){
    Glide.with(context).load(url).into(this)
}
