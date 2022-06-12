package com.example.isslocation.entity.iss.repository
import com.example.isslocation.entity.iss.ISS
import io.reactivex.Observable

interface IISSRepository {
    fun getCoordinates(): Observable<ISS>
}

//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