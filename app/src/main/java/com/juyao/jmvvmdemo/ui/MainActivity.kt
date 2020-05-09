package com.juyao.jmvvmdemo.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.juyao.jmvvm.mvvm.JActivity
import com.juyao.jmvvmdemo.adapter.BannerAdapter
import com.juyao.jmvvmdemo.adapter.MainPagerAdapter
import com.juyao.jmvvmdemo.bean.Banner
import com.juyao.jmvvmdemo.bean.Categories
import com.juyao.jmvvmdemo.databinding.ActivityMainBinding
import com.juyao.jmvvmdemo.ext.dpToPx
import com.juyao.jmvvmdemo.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.abs


class MainActivity : JActivity<MainViewModel>() {
    var bannerAdapter:BannerAdapter?=null
    val maxRito=0.8f
    override fun initData(savedInstanceState: Bundle?) {
        toolbar.title="干货集中营"
        toolbar.setTitleTextColor(Color.WHITE)
        viewModel.getBanner()
        val mAnimator=ViewPager2.PageTransformer { page, position ->
            Log.i("MainActivity","page=$page,position=$position")
            val absPos= abs(position)
            page.scaleX=if(1-absPos<maxRito){
                maxRito
            }else{
                1-absPos
            }
            page.scaleY=if(1-absPos<maxRito){
                maxRito
            }else{
                1-absPos
            }
        }
        (binding as ActivityMainBinding).banner.apply {
            offscreenPageLimit=1
            setPageTransformer(mAnimator)
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    Log.i("BannerDebug","position==$position")
                    super.onPageSelected(position)
                    if(adapter!!.itemCount>1){
                        if(position==0){
                            lifecycleScope.launch {
                                delay(200)
                                withContext(Dispatchers.Main){
                                    setCurrentItem(adapter!!.itemCount-2,false)
                                }
                            }

                        }else if(position==adapter!!.itemCount-1){
                            lifecycleScope.launch {
                                delay(200)
                                withContext(Dispatchers.Main){
                                    setCurrentItem(1,false)
                                }
                            }

                        }
                    }
                }
            })
        }
        viewModel.bannerData.observe(this,
            Observer<List<Banner>> {
                if(null==bannerAdapter){
                    bannerAdapter= BannerAdapter(context)
                }
                (binding as ActivityMainBinding).banner.adapter=bannerAdapter
                bannerAdapter!!.setData(it)
                (binding as ActivityMainBinding).banner.setCurrentItem(1,false)
            })
        viewModel.getCategories()
        (binding as ActivityMainBinding).mainPager.offscreenPageLimit=2
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

    override fun getViewBinding(): ActivityMainBinding =ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModel(): MainViewModel = ViewModelProvider(this).get(
        MainViewModel::class.java)

}
