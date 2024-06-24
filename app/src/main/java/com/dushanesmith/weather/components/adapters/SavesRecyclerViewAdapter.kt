package com.dushanesmith.weather.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dushanesmith.weather.R
import com.dushanesmith.weather.data.weather.WeatherSave

class SavesRecyclerViewAdapter : RecyclerView.Adapter<SavesRecyclerViewAdapter.ViewHolder>() {

    var data = ArrayList<WeatherSave>()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val locationName = itemView.findViewById<TextView>(R.id.locationName)
        fun bind(weatherSave: WeatherSave){
            locationName.text = weatherSave.location

            itemView.setOnClickListener{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.cardview_item_save,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}