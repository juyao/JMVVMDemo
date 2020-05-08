package com.juyao.jmvvmdemo.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.juyao.jmvvm.mvvm.JActivity
import com.juyao.jmvvmdemo.adapter.BannerAdapter
import com.juyao.jmvvmdemo.adapter.MainPagerAdapter
import com.juyao.jmvvmdemo.bean.Banner
import com.juyao.jmvvmdemo.bean.Categories
import com.juyao.jmvvmdemo.viewmodel.MainViewModel
import com.juyao.jmvvmdemo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope

class MainActivity : JActivity<MainViewModel>() {
    var bannerAdapter:BannerAdapter?=null
    override fun initData(savedInstanceState: Bundle?) {
        toolbar.title="干货集中营"
        toolbar.setTitleTextColor(Color.WHITE)
        viewModel.getBanner()
        viewModel.bannerData.observe(this,
            Observer<List<Banner>> {
                if(null==bannerAdapter){
                    bannerAdapter= BannerAdapter(context)
                }
                (binding as ActivityMainBinding).banner.adapter=bannerAdapter
                bannerAdapter!!.setData(it)
            })
        viewModel.getCategories()
        viewModel.categoriesData.observe(this,Observer<List<Categories>>{
            val mainPagerAdapter=MainPagerAdapter(context as MainActivity)
            mainPagerAdapter.categoriesList=it
            main_pager.adapter=mainPagerAdapter
            tablayout.tabMode=TabLayout.MODE_AUTO

            TabLayoutMediator(tablayout,main_pager){
            tab, position ->
            tab.text=it[position].title
        }.attach()
        })

    }

    override fun getViewBinding(): ActivityMainBinding? =ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModel(): MainViewModel = ViewModelProvider(this).get(
        MainViewModel::class.java)
}
