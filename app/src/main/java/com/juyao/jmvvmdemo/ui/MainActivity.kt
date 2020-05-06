package com.juyao.jmvvmdemo.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.juyao.jmvvm.mvvm.JActivity
import com.juyao.jmvvmdemo.viewmodel.MainViewModel
import com.juyao.jmvvmdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope

class MainActivity : JActivity<MainViewModel>() {
    override fun initData(savedInstanceState: Bundle?) {
        viewModel.getBanner()
    }

    override fun getViewBinding(): ViewBinding? =ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModel(): MainViewModel = ViewModelProvider(this).get(
        MainViewModel::class.java)
}
