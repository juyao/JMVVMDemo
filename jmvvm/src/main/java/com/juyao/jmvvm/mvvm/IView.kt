package com.juyao.jmvvm.mvvm

import android.os.Bundle
import androidx.viewbinding.ViewBinding


/**
 *
 *
 *Created by juyao on 2017/6/28 at 15:53.\n
 * 邮箱:juyao0909@gmail.com
 */
interface IView<out VM>{
    fun initData(savedInstanceState:Bundle?)
    fun getOptionsMenuId():Int
    fun getViewBinding():ViewBinding?
    fun getViewModel():VM
}


