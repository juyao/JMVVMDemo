package com.juyao.jmvvm.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class JFragment<VM: ViewModel>: Fragment(), IView<VM> {
    val viewModel:VM by lazy {
        getViewModel()
    }
    val context:FragmentActivity? by lazy {
        activity
    }
    override fun getOptionsMenuId(): Int =0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData(savedInstanceState)
    }

    override fun getViewBinding(): ViewBinding? {
        return null
    }







    
}