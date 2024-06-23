package com.dushanesmith.weather

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dushane.weather.data.weather.Daily

class WeatherRecyclerViewAdapter: RecyclerView.Adapter<WeatherRecyclerViewAdapter.ViewHolder>() {
    var data: ArrayList<Daily> = arrayListOf<Daily>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_item_weather, parent, false)
        return ViewHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(val context:Context, val itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.weatherImage)
        val currentTempResult = itemView.findViewById<TextView>(R.id.tempResult)
        val weatherDescription = itemView.findViewById<TextView>(R.id.weatherDescription)
        val windDirectionResult = itemView.findViewById<TextView>(R.id.windDirectionResult)
        val windSpeedResult = itemView.findViewById<TextView>(R.id.windSpeedResult)

        fun bind(daily: Daily){
            val imageUrl = "https://openweathermap.org/img/wn/${daily.weather.get(0).icon}@2x.png"

            Glide.with(context).load(imageUrl).into(imageView)
            currentTempResult.text = daily.temp?.day.toString()
            weatherDescription.text = daily.summary.toString()
            windDirectionResult.text = daily.windDeg?.toString() + "°"
            windSpeedResult.text = daily.windSpeed?.toString()
        }
    }
}