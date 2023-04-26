package com.example.weatherapp.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SPManager @Inject constructor (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences("string_last_access", Context.MODE_PRIVATE)
    companion object {
        private const val USER_LAST_ACCESS_TOKEN = "user_last_access_token"
    }

    /**
     * Function to add storeId
     */
    fun addLastAccessCity(city: String) {
        val editor = prefs.edit()
        editor.putString(USER_LAST_ACCESS_TOKEN, city)
        editor.apply()
    }

    /**
     * Function to remove storeId
     */
    fun getLastAccessCity(): String {
        prefs.getString(USER_LAST_ACCESS_TOKEN, null)?.let {
            return it
        }
        return ""
    }
}