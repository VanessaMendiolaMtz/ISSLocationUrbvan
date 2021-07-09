package com.example.isslocation.entity.repository
import com.example.isslocation.entity.ISS
import io.reactivex.Observable

interface IISSRepository {
    fun getCoordinates(): Observable<ISS>
}