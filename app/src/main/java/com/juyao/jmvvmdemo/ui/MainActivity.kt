package com.juyao.jmvvmdemo.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.juyao.jmvvm.mvvm.JActivity
import com.juyao.jmvvmdemo.adapter.BannerAdapter
import com.juyao.jmvvmdemo.bean.Banner
import com.juyao.jmvvmdemo.viewmodel.MainViewModel
import com.juyao.jmvvmdemo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope

class MainActivity : JActivity<MainViewModel>() {
    var bannerAdapter:BannerAdapter?=null
    override fun initData(savedInstanceState: Bundle?) {
        viewModel.getBanner()
        viewModel.bannerData.observe(this,
            Observer<List<Banner>> {
                if(null==bannerAdapter){
                    bannerAdapter= BannerAdapter(context)
                }
                banner.adapter=bannerAdapter
                bannerAdapter!!.setData(it)
            })
    }

    override fun getViewBinding(): ViewBinding? =ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModel(): MainViewModel = ViewModelProvider(this).get(
        MainViewModel::class.java)
}
