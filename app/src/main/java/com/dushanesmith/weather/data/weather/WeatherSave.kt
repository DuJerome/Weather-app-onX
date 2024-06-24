package com.dushanesmith.weather.data.weather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dushane.weather.data.weather.Daily

@Entity
data class WeatherSave (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("locationID") var id:Int = 0,
    @ColumnInfo("location") var location: String? = null,
    @ColumnInfo("dailyList") var dailyList: Array<Daily> = arrayOf()
)
