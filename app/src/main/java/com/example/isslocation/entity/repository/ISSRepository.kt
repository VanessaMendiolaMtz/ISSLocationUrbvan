package com.example.isslocation.entity.repository
import com.example.isslocation.api.ISSLocationApi
import com.example.isslocation.entity.ISS
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class ISSRepository(private var retrofit : Retrofit): IISSRepository {
    override fun getCoordinates(): Observable<ISS> {
        return retrofit.create(ISSLocationApi.GetCoordinates::class.java).getCoordinates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { getCoordinatesResponse ->
                    return@flatMap Observable.just(getCoordinatesResponse.issPosition)
                }
    }
}