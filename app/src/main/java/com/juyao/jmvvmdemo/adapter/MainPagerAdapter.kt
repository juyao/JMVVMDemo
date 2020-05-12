package com.juyao.jmvvmdemo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.juyao.jmvvmdemo.bean.Categories
import com.juyao.jmvvmdemo.ui.ArticleListFragment

class MainPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    var categoriesList:List<Categories> =ArrayList()
    var fragmentList:ArrayList<ArticleListFragment> = ArrayList()
    override fun getItemCount(): Int =categoriesList.size

    override fun createFragment(position: Int): Fragment {
        val fragment:ArticleListFragment=ArticleListFragment.newInstance(categoriesList[position].type)
        fragmentList.add(fragment)
        return fragment
    }





}