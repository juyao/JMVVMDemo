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
        var realPosition = if(itemCount==0||itemCount==1){
            position
        }else{
            when (position) {
                0 -> {
                    itemCount-3
                }
                itemCount-1 -> {
                    0
                }
                else -> {
                    position-1
                }
            }
        }

        val banner=dataList[realPosition]
        (holder.itemView.img_banner as ImageView).load(banner.image)
        holder.itemView.text_title.text=banner.title
        holder.itemView.setOnClickListener {
            recItemClick?.onItemClick(realPosition,banner,0,holder)
        }
    }

    override fun getLayoutId(): Int = R.layout.listitem_banner
    override fun getItemCount(): Int {
        val count= super.getItemCount()
        return if(count>1){
            count+2
        }else{
            count
        }
    }
}
class BannerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}