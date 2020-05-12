package com.juyao.jmvvmdemo.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.juyao.jmvvm.adapter.RecyclerItemCallback
import com.juyao.jmvvm.mvvm.JActivity
import com.juyao.jmvvmdemo.adapter.BannerAdapter
import com.juyao.jmvvmdemo.adapter.BannerViewHolder
import com.juyao.jmvvmdemo.adapter.MainPagerAdapter
import com.juyao.jmvvmdemo.bean.Banner
import com.juyao.jmvvmdemo.bean.Categories
import com.juyao.jmvvmdemo.databinding.ActivityMainBinding
import com.juyao.jmvvmdemo.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.abs


class MainActivity : JActivity<MainViewModel>() {
    var bannerAdapter:BannerAdapter?=null
    val maxRito=0.8f
    val tickChannel by lazy {
        ticker(3000,3000)
    }
    val mainPagerAdapter by lazy {
        MainPagerAdapter(context as MainActivity)
    }
    val binding by lazy { 
        jBinding as ActivityMainBinding
    }
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
        binding.banner.apply {
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
            lifecycleScope.launch(Dispatchers.Main){
                for(event in tickChannel){
                    binding.banner.currentItem= binding.banner.currentItem+1
                }
            }

        }

        viewModel.bannerData.observe(this,
            Observer<List<Banner>> {
                if(null==bannerAdapter){
                    bannerAdapter= BannerAdapter(context)
                }
                binding.banner.adapter=bannerAdapter
                bannerAdapter!!.setData(it)
                bannerAdapter!!.recItemClick=object:RecyclerItemCallback<Banner,BannerViewHolder>{
                    override fun onItemClick(
                        position: Int,
                        model: Banner,
                        tag: Int,
                        holder: BannerViewHolder
                    ) {
                        val intent=Intent(context,WebViewActivity::class.java)
                        intent.putExtra("url",model.url)
                        startActivity(intent)
                    }

                    override fun onItemLongClick(
                        position: Int,
                        model: Banner,
                        tag: Int,
                        holder: BannerViewHolder
                    ) {

                    }

                }
                binding.banner.setCurrentItem(1,false)
            })
        viewModel.getCategories()
        binding.mainPager.offscreenPageLimit=2
        viewModel.categoriesData.observe(this,Observer<List<Categories>>{
            mainPagerAdapter.categoriesList=it
            main_pager.adapter=mainPagerAdapter
            tablayout.tabMode=TabLayout.MODE_AUTO
            TabLayoutMediator(tablayout,main_pager){
            tab, position ->
            tab.text=it[position].title
        }.attach()
        })
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getBanner()
            mainPagerAdapter.fragmentList[binding.mainPager.currentItem].refresh()
            lifecycleScope.launch(Dispatchers.Main){
                delay(2000)
                binding.refreshLayout.finishRefresh()
            }
        }

    }

    override fun getViewBinding(): ActivityMainBinding =ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModel(): MainViewModel = ViewModelProvider(this).get(
        MainViewModel::class.java)


}
