package com.juyao.jmvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


/**
 *
 *
 *Created by juyao on 2017/11/26 at 20:26.\n
 * 邮箱:juyao0909@gmail.com
 */


abstract class SimpleRecAdapter<T,F: RecyclerView.ViewHolder>(context:Context): BaseRecAdapter<T, F>(context) {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): F {
        val view = LayoutInflater.from(p0!!.context).inflate(getLayoutId(),p0,false)
        return newViewHolder(view)
    }
    abstract fun getLayoutId():Int
    abstract fun newViewHolder(itemView:View):F


}