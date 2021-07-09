package com.example.isslocation.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.isslocation.R
import com.example.isslocation.di.ISSLocationApplication
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    private val viewModel by viewModels<MapsViewModel> {viewModelFactory}

    private lateinit var map:GoogleMap

    private val ZOOM: Float = 7F

    override fun onCreate(savedInstanceState: Bundle?){
        (application as ISSLocationApplication).getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        createMap()
        viewModel.updateLocation()
        setUpObservables()
    }

    private fun createMap(){
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    private fun setUpObservables(){
        viewModel.latlng.observe(this, { latlng ->
            map.clear()
            map.addMarker(MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)).title("International Space Station"))
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, ZOOM), 4000, null)
            Toast.makeText(this, latlng.toString(), Toast.LENGTH_LONG).show()
        })

        viewModel.errorMessage.observe(this, {errorMessage->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })
    }
}