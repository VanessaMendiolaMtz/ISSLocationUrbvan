package com.example.isslocation.di

import android.app.Application
import com.example.isslocation.di.modules.AppModule
import com.example.isslocation.di.modules.NetworkModule
import com.example.isslocation.di.modules.RepositoryModule

class ISSLocationApplication : Application() {
    lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .repositoryModule(RepositoryModule())
            .networkModule(NetworkModule())
            .build()
    }

    fun getComponent() = applicationComponent
}