package com.example.isslocation.di.modules

import com.example.isslocation.entity.repository.ISSRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RepositoryModule {
    @Provides
    fun issLocationProvider(retrofit: Retrofit) = ISSRepository(retrofit)
}
