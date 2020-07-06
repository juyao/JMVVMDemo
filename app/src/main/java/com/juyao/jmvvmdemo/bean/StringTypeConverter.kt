package com.juyao.jmvvmdemo.bean

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class StringTypeConverter {
    @TypeConverter
    fun  stringToSomeObjectList(data:String):List<String>{
        if(data==null){
            return Collections.emptyList()
        }
        val listType= object:TypeToken<List<String>>(){}.type
        return Gson().fromJson(data,listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<String?>?): String? {
        return Gson().toJson(someObjects)
    }

}