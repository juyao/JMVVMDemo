package com.juyao.jmvvmdemo.ext

import android.content.Context
import android.util.TypedValue

fun Int.dpToPx(context:Context):Int{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.toFloat(),context.resources.displayMetrics).toInt()
}