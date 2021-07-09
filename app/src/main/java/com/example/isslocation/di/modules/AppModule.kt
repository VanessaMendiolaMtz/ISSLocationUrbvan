package com.example.isslocation.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule (private val app : Application){
    @Provides
    fun applicationContextProvider() = app.applicationContext!!
}