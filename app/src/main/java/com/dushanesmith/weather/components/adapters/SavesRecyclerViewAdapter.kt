package com.dushanesmith.weather.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dushanesmith.weather.R
import com.dushanesmith.weather.data.weather.WeatherSave
import com.dushanesmith.weather.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SavesRecyclerViewAdapter(
    val homeViewModel: HomeViewModel,
    val weatherRecyclerViewAdapter: WeatherRecyclerViewAdapter,
    val bottomNavigationView: BottomNavigationView
) : RecyclerView.Adapter<SavesRecyclerViewAdapter.ViewHolder>() {
    var data = arrayOf<WeatherSave>()

    class ViewHolder(itemView: View,val homeViewModel: HomeViewModel) : RecyclerView.ViewHolder(itemView) {
        val locationName = itemView.findViewById<TextView>(R.id.locationName)
        val closeButton = itemView.findViewById<ImageButton>(R.id.buttonClose)
        fun bind(weatherSave: WeatherSave){
        }
    }

    fun updateList(saves: Array<WeatherSave>){
        data = saves
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.cardview_item_save,
                parent,
                false
            )
        return ViewHolder(view, homeViewModel)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])

        holder.locationName.text = data[position].location
        holder.closeButton.setOnClickListener {
            homeViewModel.delete(data[position])
            data = homeViewModel.getAllSaves()
            notifyItemRemoved(position)
        }

        holder.itemView.setOnClickListener{
            weatherRecyclerViewAdapter.data = data[position].dailyList
            bottomNavigationView.selectedItemId = R.id.checkWeatherForLocation
            weatherRecyclerViewAdapter.notifyDataSetChanged()
        }
    }
}