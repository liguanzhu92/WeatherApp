package com.example.weatherapp.dagger

import android.app.Application
import android.content.Context
import com.example.weatherapp.WeatherApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val application: WeatherApp) {
    @Provides
    @Singleton
    fun getApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun providesApplicationContext(): Context {
        return application
    }
}