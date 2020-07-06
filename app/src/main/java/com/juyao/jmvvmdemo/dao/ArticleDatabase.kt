package com.juyao.jmvvmdemo.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juyao.jmvvmdemo.bean.Article

@Database(entities = arrayOf(Article::class),version = 1)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun articleDao():ArticleDao
    companion object{
        @Volatile
        private var INSTANCE:ArticleDatabase?=null
        fun getInstance(context:Context):ArticleDatabase= INSTANCE ?: synchronized(this){
            INSTANCE?: buildDatabase(context).also { INSTANCE= it }
        }
        private fun buildDatabase(context: Context)=Room.databaseBuilder(context.applicationContext,ArticleDatabase::class.java,"Article.db").build()
    }
}