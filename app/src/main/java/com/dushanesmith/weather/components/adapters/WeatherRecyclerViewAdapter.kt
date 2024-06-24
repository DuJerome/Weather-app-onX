package com.dushanesmith.weather.components.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dushane.weather.data.weather.Daily
import com.dushanesmith.weather.R
import java.util.Calendar


class WeatherRecyclerViewAdapter: RecyclerView.Adapter<WeatherRecyclerViewAdapter.ViewHolder>() {
    var data: ArrayList<Daily> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_item_weather, parent, false)
        return ViewHolder(parent.context, view, this::date)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun date(timeToAdd: Int): String {
        val calendar = Calendar.getInstance()
        val stringBuilder = StringBuilder()
        calendar.add(Calendar.DATE,timeToAdd)
        stringBuilder.append(calendar.get(Calendar.MONTH)+1).append("/")
        stringBuilder.append(calendar.get(Calendar.DATE))
        return stringBuilder.toString()
    }

    class ViewHolder(val context:Context, val itemView: View, val date: (timeToAdd: Int) -> String) : RecyclerView.ViewHolder(itemView){
        val dateText = itemView.findViewById<TextView>(R.id.date)
        val imageView = itemView.findViewById<ImageView>(R.id.weatherImage)
        val currentTempResult = itemView.findViewById<TextView>(R.id.tempResult)
        val weatherDescription = itemView.findViewById<TextView>(R.id.weatherDescription)
        val windDirectionResult = itemView.findViewById<TextView>(R.id.windDirectionResult)
        val windSpeedResult = itemView.findViewById<TextView>(R.id.windSpeedResult)

        fun bind(daily: Daily, position: Int){
            val imageUrl = "https://openweathermap.org/img/wn/${daily.weather.get(0).icon}@2x.png"

            Glide.with(context).load(imageUrl).into(imageView)
            dateText.text = date(position)
            currentTempResult.text = daily.temp?.day.toString()
            weatherDescription.text = daily.summary.toString()
            windDirectionResult.text = daily.windDeg?.toString() + "°"
            windSpeedResult.text = daily.windSpeed?.toString()
        }
    }
}