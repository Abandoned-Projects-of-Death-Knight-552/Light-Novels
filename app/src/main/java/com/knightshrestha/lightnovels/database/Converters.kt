package com.knightshrestha.lightnovels.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.Date

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }


}

class ListConverters {
    @TypeConverter
    fun listToString(list: List<String>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToList(string: String?): List<String>? {
        val listType: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(string, listType)
    }
}

class AssociatedConverters {

}