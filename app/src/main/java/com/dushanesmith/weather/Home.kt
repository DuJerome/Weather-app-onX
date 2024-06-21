package com.dushanesmith.weather

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.common.MapboxOptions
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style

class Home : AppCompatActivity(), PermissionsListener {
    lateinit var permissionsManager: PermissionsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapInitOptions = setUpInitCameraOptions()
        val mapView = MapView(this, mapInitOptions)
        setContentView(mapView)
        setUpMapBox(mapView)
        askForLocationPermissions()
    }

    fun setUpMapBox(mapView: MapView) {
        MapboxOptions.accessToken = getString(R.string.mapbox_access_token)
        mapView.mapboxMap.loadStyle(Style.MAPBOX_STREETS)
    }

    fun setUpInitCameraOptions(): MapInitOptions {
        val initialCameraOptions = CameraOptions.Builder()
            .center(Point.fromLngLat(-74.0066, 40.7135))
            .pitch(0.0)
            .zoom(10.0)
            .bearing(0.0)
            .build()
        val mapInitOptions = MapInitOptions(
            context = this,
            cameraOptions = initialCameraOptions,
            textureView = true
        )
        return mapInitOptions
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