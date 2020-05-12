package com.juyao.jmvvmdemo.adapter

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.juyao.jmvvm.adapter.SimpleRecAdapter
import com.juyao.jmvvmdemo.R
import com.juyao.jmvvmdemo.bean.Article
import com.juyao.jmvvmdemo.ext.load
import kotlinx.android.synthetic.main.listitem_articlelist.view.*

class ArticleListAdapter(context:Context) : SimpleRecAdapter<Article, ArticleViewHolder>(context) {
    override fun getLayoutId(): Int = R.layout.listitem_articlelist

    override fun newViewHolder(itemView: View): ArticleViewHolder = ArticleViewHolder(itemView)

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article=dataList[position]
        holder.itemView.apply {
            text_author.text=article.author
            text_createtime.text=article.publishedAt
            if(article.images.isNotEmpty()){
                img_cover.load(article.images[0])
            }else{
                img_cover.setImageDrawable(ColorDrawable(context.resources.getColor(R.color.colorPrimary)))
            }
            text_title.text=article.title
            text_dec.text=article.desc
            text_flag.text=article.type
            setOnClickListener {
                recItemClick?.onItemClick(position,article,0,holder)
            }
        }


    }
}

class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}