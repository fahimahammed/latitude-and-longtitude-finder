package com.example.getyourlocation

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    private lateinit var latitude : TextView
    var lat = "22.84"
    //private lateinit var longitude: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Get Location"

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        latitude = findViewById(R.id.textView)
        latitude.text = lat
        val button = findViewById<Button>(R.id.getLocation)

        button.setOnClickListener{
            getLocation()
        }

    }

    private fun getLocation(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100)
            return
        }
        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if(it!=null){
                lat = "Latitude: "+it.latitude.toString() + " Longitude: "+it.longitude.toString()
                latitude.text = lat
            }
        }

    }

}