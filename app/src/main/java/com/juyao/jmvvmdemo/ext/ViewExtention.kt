package com.juyao.jmvvmdemo.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.load(url:String){
    Glide.with(context).load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}