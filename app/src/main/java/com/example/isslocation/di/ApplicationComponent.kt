package com.example.isslocation.di

import com.example.isslocation.di.modules.AppModule
import com.example.isslocation.di.modules.NetworkModule
import com.example.isslocation.di.modules.RepositoryModule
import com.example.isslocation.di.modules.ViewModelModule
import com.example.isslocation.ui.activity.MapsActivity
import dagger.Component

@Component(modules = [
    ViewModelModule::class,
    AppModule::class,
    RepositoryModule::class,
    NetworkModule::class
])
interface ApplicationComponent {
    fun inject (target: MapsActivity)
}