package com.nhbhuiyan.newsfeed.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.nhbhuiyan.newsfeed.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {
    @TypeConverter
    fun fromSourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }
    @TypeConverter
    fun stringToSource(source: String) : Source{
        return source.split(",").let{
            Source(it[0],it[1])
        }
    }

}