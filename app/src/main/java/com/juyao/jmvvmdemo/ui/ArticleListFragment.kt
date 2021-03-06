package com.juyao.jmvvmdemo.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.juyao.jmvvm.adapter.RecyclerItemCallback
import com.juyao.jmvvm.mvvm.JFragment
import com.juyao.jmvvmdemo.adapter.ArticleListAdapter
import com.juyao.jmvvmdemo.adapter.ArticleViewHolder
import com.juyao.jmvvmdemo.bean.Article
import com.juyao.jmvvmdemo.dao.ArticleDatabase
import com.juyao.jmvvmdemo.databinding.ArticleListFragmentBinding
import com.juyao.jmvvmdemo.viewmodel.ArticleListViewModel

class ArticleListFragment : JFragment<ArticleListViewModel>{
    var category :String?=null
    var articleAdapter: ArticleListAdapter?=null
    val articleDao by lazy {
        ArticleDatabase.getInstance(requireContext()).articleDao()
    }
    constructor(category: String?) : super() {
        this.category = category
    }

    companion object {
        fun newInstance(category: String?) = ArticleListFragment(category)
    }
    lateinit var binding:ArticleListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= ArticleListFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initData(savedInstanceState: Bundle?) {
        val layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.articleListview.layoutManager=layoutManager
        articleAdapter= context?.let { ArticleListAdapter(it) }
        binding.articleListview.adapter=articleAdapter
        viewModel.articleData.observe(this,
            Observer<List<Article>> { t -> articleAdapter!!.setData(t)
            articleAdapter!!.recItemClick=object:RecyclerItemCallback<Article,ArticleViewHolder>{
                override fun onItemClick(
                    position: Int,
                    model: Article,
                    tag: Int,
                    holder: ArticleViewHolder
                ) {
                    val intent= Intent(context,WebViewActivity::class.java)
                    intent.putExtra("url",model.url)
                    startActivity(intent)
                }

                override fun onItemLongClick(
                    position: Int,
                    model: Article,
                    tag: Int,
                    holder: ArticleViewHolder
                ) {
                    val dialogBuilder=AlertDialog.Builder(context!!)
                        .setMessage("确认收藏这篇文章吗？")
                        .setCancelable(true)
                        .setPositiveButton("确定"
                        ) { _, _ ->
                            articleDao.insertArticle(model)
                        }
                        .setNegativeButton("取消"
                        ) { dialog, _ -> dialog?.dismiss() }
                    dialogBuilder.create().show()

                }

            }})
        viewModel.getArticleList(category!!,1)


    }
    fun refresh(){
        viewModel.getArticleList(category!!,1)
    }

    override fun getViewModel(): ArticleListViewModel=ViewModelProvider(this).get(ArticleListViewModel::class.java)

}
