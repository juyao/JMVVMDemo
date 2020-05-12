package com.juyao.jmvvm.mvvm

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class JActivity<VM:ViewModel?>: FragmentActivity(), IView<VM> {
    val viewModel:VM by lazy {
        getViewModel()
    }
    val context:Context by lazy {
        this
    }
    val jBinding:ViewBinding? by lazy {
        getViewBinding()
    }
    val TAG by lazy {
        localClassName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jBinding?.let {
            setContentView(it.root)
        }
        initData(savedInstanceState)
    }

    override fun getOptionsMenuId(): Int = 0


}