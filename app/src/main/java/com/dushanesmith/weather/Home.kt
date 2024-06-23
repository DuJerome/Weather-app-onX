package com.dushanesmith.weather

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dushane.weather.data.weather.Daily
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.common.MapboxOptions
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : FragmentActivity(), PermissionsListener {
    val homeViewModel by viewModels<HomeViewModel>()
    lateinit var permissionsManager: PermissionsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val mapView = findViewById<MapView>(R.id.mapView)
        askForLocationPermissions()
        val weatherRecyclerViewAdapter = setupWeatherRecyclerView()
        setUpMapBox(mapView, weatherRecyclerViewAdapter)
    }

    fun setUpMapBox(mapView: MapView, weatherRecyclerViewAdapter: WeatherRecyclerViewAdapter) {
        MapboxOptions.accessToken = getString(R.string.mapbox_access_token)
        mapView.mapboxMap.loadStyle(Style.MAPBOX_STREETS)
        mapView.mapboxMap.addOnMapClickListener(object : OnMapClickListener {
            override fun onMapClick(point: Point): Boolean {
                weatherRecyclerViewAdapter.data = homeViewModel.getWeatherResults(
                    point.latitude().toString(),
                    point.longitude().toString()
                ).daily as ArrayList<Daily>
                weatherRecyclerViewAdapter.notifyDataSetChanged()
                return true
            }
        })
    }

    fun setupWeatherRecyclerView(): WeatherRecyclerViewAdapter{
        val weatherRecyclerViewAdapter = WeatherRecyclerViewAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewWeather)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.adapter = weatherRecyclerViewAdapter
        return weatherRecyclerViewAdapter
    }

    fun askForLocationPermissions() {
        if (PermissionsManager.areLocationPermissionsGranted(applicationContext)) {
            // Permission sensitive logic called here, such as activating the Maps SDK's LocationComponent to show the device's location
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