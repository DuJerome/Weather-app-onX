package com.dushanesmith.weather.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dushanesmith.weather.R
import com.dushanesmith.weather.data.weather.WeatherSave
import com.dushanesmith.weather.databinding.ActivityHomeBinding
import com.dushanesmith.weather.viewmodel.HomeViewModel

class SavesRecyclerViewAdapter(
    val binding: ActivityHomeBinding,
    val homeViewModel: HomeViewModel,
    val weatherRecyclerViewAdapter: WeatherRecyclerViewAdapter,
) : RecyclerView.Adapter<SavesRecyclerViewAdapter.ViewHolder>() {
    var data = arrayOf<WeatherSave>()

    class ViewHolder(itemView: View,val homeViewModel: HomeViewModel) : RecyclerView.ViewHolder(itemView) {
        val locationName = itemView.findViewById<TextView>(R.id.textViewName)
        val closeButton = itemView.findViewById<ImageButton>(R.id.buttonClose)
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
        val weatherSave = data[position]
        holder.locationName.text = data[position].location
        holder.closeButton.setOnClickListener {
            homeViewModel.delete(weatherSave)
            data = homeViewModel.getAllSaves()
            notifyDataSetChanged()
            if(data.isEmpty()){
                binding.bottomNavigationView.selectedItemId = R.id.checkWeatherForLocation
            }
        }

        holder.itemView.setOnClickListener{
            weatherRecyclerViewAdapter.data = data[position].dailyList
            binding.bottomNavigationView.selectedItemId = R.id.checkWeatherForLocation
            weatherRecyclerViewAdapter.notifyDataSetChanged()
        }
    }
}