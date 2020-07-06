package com.juyao.jmvvmdemo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juyao.jmvvmdemo.bean.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertArticle(article:Article)
    @Query("SELECT * FROM Article")
     fun getAllArticle():LiveData<List<Article>>
}