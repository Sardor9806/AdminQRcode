package com.example.qradmin11.googleMap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qradmin11.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.qradmin11.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var x:String=""
    private var y:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        x= intent.getStringExtra("x").toString()
        y=intent.getStringExtra("y").toString()
        if(x=="" && y=="")
        {
            x=12.0.toString()
            y=12.0.toString()
        }
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(x.toDouble(), y.toDouble())
        mMap.addMarker(MarkerOptions().position(sydney).title("User"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,12.0f))
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
    }
}