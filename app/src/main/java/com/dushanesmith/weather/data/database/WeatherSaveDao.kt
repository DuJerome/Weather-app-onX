package com.dushanesmith.weather.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dushanesmith.weather.data.weather.WeatherSave

@Dao
interface WeatherSaveDao {
    @Query("SELECT * FROM WeatherSave")
    fun getAll(): Array<WeatherSave>

    @Insert
    fun insert(vararg saves: WeatherSave)

    @Delete
    fun delete(saves: WeatherSave)
}