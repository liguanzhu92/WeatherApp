package com.example.weatherapp.dagger

import android.content.Context
import com.example.weatherapp.data.DataRepository
import com.example.weatherapp.network.CallService
import com.example.weatherapp.utils.SPManager
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideDataRepository(service: CallService): DataRepository {
        return DataRepository(service)
    }

    @Provides
    fun provideSPManager(context: Context): SPManager {
        return SPManager(context)
    }
}