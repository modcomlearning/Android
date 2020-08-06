package com.joseph.stagelocator


import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    //this will help access your GPS on your phone
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    //Get last known location, helps retrieving current location
    private lateinit var lastlocation: Location


    //We need to seek user permissions
    //this function is called/used in onMapready
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1)
            return
        }//end if

        //make location enabled
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            // 3
            if (location != null) {
                lastlocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                //save to DB
                //bluetooth
                //print above latitude
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,
                    12f))
            }
        }

    }//end function



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this) //load map in bits, sections
        //below means we can access your phone GPs tracker
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

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
        val chiromo = LatLng(-1.269650, 36.808920)
        mMap.addMarker(MarkerOptions().position(chiromo).title("Chiromo Bus Terminus")
            .snippet("To: Kagemi, Ruaka, Waiyaki Way, ")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus1)))

        //another stage
        val westiemain = LatLng(-1.2654394,36.8006158)
        mMap.addMarker(MarkerOptions().position(westiemain).title("Westlands Main Terminus")
            .snippet("To: Kagemi, Town, ")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus1)))


        val stmarks = LatLng(-1.2605439,36.7948584)
        mMap.addMarker(MarkerOptions().position(stmarks).title("ST Marks Terminus")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus1)))


       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chiromo, 14f))
           // check latlong.net

        setUpMap() //trigger function done on line 31
    }
}