package com.example.isslocation.api

import com.example.isslocation.entity.getCoordinates.ISSLocationResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ISSLocationApi {
    interface GetCoordinates{
        @GET("iss-now.json")
        fun getCoordinates() : Observable<ISSLocationResponse>
    }
}