package com.example.isslocation.ui.activity.maps

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.isslocation.entity.iss.repository.ISSRepository
import com.google.android.gms.maps.model.LatLng
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MapsViewModel @Inject constructor(private val repository : ISSRepository) : ViewModel() {

    private val _latlng = MutableLiveData<LatLng>()
    val latlng: LiveData<LatLng> get() = _latlng

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> get() = _errorMessage

    private val compositeDisposable = CompositeDisposable()

    fun updateLocation() {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                getCoordinates()
                handler.postDelayed(this, 10000)
            }
        }, 0)
    }

    fun getCoordinates(){
        compositeDisposable.add(repository.getCoordinates()
            .subscribe({ issPosition ->
                _latlng.postValue(LatLng(issPosition.issLatitude!!.toDouble(), issPosition.issLongitude!!.toDouble()))
            }, {
                _errorMessage.postValue("Error al cargar la ubicación")
            })
        )
    }
}