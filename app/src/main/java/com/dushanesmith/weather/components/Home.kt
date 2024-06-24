package com.dushanesmith.weather.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dushanesmith.weather.R
import com.dushanesmith.weather.components.adapters.SavesRecyclerViewAdapter
import com.dushanesmith.weather.components.adapters.WeatherRecyclerViewAdapter
import com.dushanesmith.weather.data.weather.WeatherSave
import com.dushanesmith.weather.databinding.ActivityHomeBinding
import com.dushanesmith.weather.viewmodel.HomeViewModel
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.common.MapboxOptions
import com.mapbox.geojson.Point
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : FragmentActivity(), PermissionsListener {
    val homeViewModel by viewModels<HomeViewModel>()
    lateinit var binding: ActivityHomeBinding
    lateinit var permissionsManager: PermissionsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        askForLocationPermissions()
        val weatherRecyclerViewAdapter = setupWeatherRecyclerView(binding)
        setUpMapBox(binding, weatherRecyclerViewAdapter)
        val savesRecyclerViewAdapter = setupSavesRecyclerView(binding, weatherRecyclerViewAdapter)
        binding.saveButton.setOnClickListener {
            homeViewModel.insertSave(WeatherSave(location =homeViewModel.currentLocationCity, dailyList = weatherRecyclerViewAdapter.data))
        }

        binding.bottomNavigationView.setOnItemSelectedListener(object: OnItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.checkWeatherForLocation){
                    binding.recyclerViewWeather.visibility = View.VISIBLE
                    binding.saveButton.visibility = View.VISIBLE
                    binding.recyclerViewSaves.visibility = View.GONE
                    binding.cardViewSaves.visibility = View.GONE

                }else if(item.itemId == R.id.saveMenu){
                    binding.recyclerViewWeather.visibility = View.GONE
                    binding.saveButton.visibility = View.GONE
                    binding.recyclerViewSaves.visibility = View.VISIBLE
                    binding.cardViewSaves.visibility = View.VISIBLE
                    savesRecyclerViewAdapter.updateList(homeViewModel.getAllSaves())
                }
                return true
            }
        })
    }

    fun setUpMapBox(binding: ActivityHomeBinding, weatherRecyclerViewAdapter: WeatherRecyclerViewAdapter) {
        MapboxOptions.accessToken = getString(R.string.mapbox_access_token)
        binding.mapView.mapboxMap.loadStyle(Style.MAPBOX_STREETS)

        binding.mapView.mapboxMap.addOnMapClickListener(object : OnMapClickListener {
            override fun onMapClick(point: Point): Boolean {
                val dailys = homeViewModel.getWeatherResults(
                    point.latitude().toString(),
                    point.longitude().toString()
                ).daily.toTypedArray()
                weatherRecyclerViewAdapter.data =dailys
                if(dailys.isEmpty()){
                    binding.saveButton.visibility = View.GONE
                    binding.recyclerViewWeather.visibility = View.GONE
                }else{
                    binding.saveButton.visibility = View.VISIBLE
                    binding.recyclerViewWeather.visibility = View.VISIBLE
                    binding.recyclerViewSaves.visibility = View.GONE
                    binding.cardViewSaves.visibility = View.GONE
                }
                weatherRecyclerViewAdapter.notifyDataSetChanged()
                return true
            }
        })
    }

    fun setupSavesRecyclerView(binding: ActivityHomeBinding, weatherRecyclerViewAdapter: WeatherRecyclerViewAdapter): SavesRecyclerViewAdapter {
        val savesRecyclerViewAdapter = SavesRecyclerViewAdapter(homeViewModel, weatherRecyclerViewAdapter, binding.bottomNavigationView)
        val recyclerView = binding.recyclerViewSaves
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = savesRecyclerViewAdapter
        return savesRecyclerViewAdapter
    }

    fun setupWeatherRecyclerView(binding: ActivityHomeBinding): WeatherRecyclerViewAdapter {
        val weatherRecyclerViewAdapter = WeatherRecyclerViewAdapter()
        val recyclerView = binding.recyclerViewWeather
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.adapter = weatherRecyclerViewAdapter
        return weatherRecyclerViewAdapter
    }

    fun askForLocationPermissions() {
        if (PermissionsManager.areLocationPermissionsGranted(applicationContext)) {
        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(this)
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: List<String>) {
        run {
            for (explanation in permissionsToExplain) {
                Toast.makeText(this, explanation, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permission not Granted", Toast.LENGTH_SHORT).show()
        }
    }
}