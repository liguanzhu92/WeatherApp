package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.dagger.AppComponent
import com.example.weatherapp.dagger.AppModule
import com.example.weatherapp.dagger.DaggerAppComponent

class WeatherApp: Application() {
    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerAppComponent()
    }

    private fun initDaggerAppComponent(): AppComponent {
        appComponent =
            DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        return appComponent
    }
}