package com.juyao.jmvvmdemo.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.juyao.jmvvm.adapter.SimpleRecAdapter
import com.juyao.jmvvmdemo.R
import com.juyao.jmvvmdemo.bean.Banner
import com.juyao.jmvvmdemo.ext.load
import kotlinx.android.synthetic.main.listitem_banner.view.*

class BannerAdapter(context:Context): SimpleRecAdapter<Banner, BannerViewHolder>(context) {

    override fun newViewHolder(itemView: View): BannerViewHolder = BannerViewHolder(itemView)

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner=dataList[position]
        (holder.itemView.img_banner as ImageView).load(banner.image)
        holder.itemView.text_title.text=banner.title
    }

    override fun getLayoutId(): Int = R.layout.listitem_banner
}
class BannerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}