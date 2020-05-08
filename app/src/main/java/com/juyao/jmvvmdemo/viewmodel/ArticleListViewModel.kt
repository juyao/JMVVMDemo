package com.juyao.jmvvmdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juyao.jmvvmdemo.bean.Article
import com.juyao.jmvvmdemo.net.Api

class ArticleListViewModel : BaseViewModel() {
    val articleData:MutableLiveData<List<Article>> by lazy {
        MutableLiveData<List<Article>>()
    }
    fun getArticleList(type:String,page:Int){
        apiRequest({Api.getNetService().getArticles(type,page)},{
            articleData.value=it
        })
    }
}
