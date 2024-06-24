package com.dushanesmith.weather.data.database

import androidx.room.TypeConverter
import com.dushane.weather.data.weather.Daily
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class DailyWeatherTypeConverter {
    val gson: Gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): Array<Daily> {
        if (data == null) {
            return emptyArray<Daily>()
        }

        val arrayType: Type = object : TypeToken<Array<Daily?>?>() {}.type

        return gson.fromJson(data, arrayType)
    }

    @TypeConverter
    fun dailyListToString(someObjects: Array<Daily?>?): String {
        return gson.toJson(someObjects)
    }
}