package com.emirozturk.country.util
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class AppSharedPreferences {
    companion object {
        private val timeName = "time"
        private var sharedPreferences: SharedPreferences? = null
        @Volatile private var instance: AppSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context): AppSharedPreferences = instance ?: synchronized(lock) {
            instance ?: make(context).also {
                instance = it
            }
        }

        private fun make(context: Context): AppSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return AppSharedPreferences()
        }
    }

    fun saveTime(time: Long) {
        sharedPreferences?.edit(true) {
            putLong(timeName, time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(timeName, -1)
}