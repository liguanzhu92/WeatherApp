package com.example.weatherapp.dagger

import com.example.weatherapp.WeatherApp
import com.example.weatherapp.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun appModule(module: AppModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: WeatherApp)
    fun inject(mainFragment: MainFragment)
}