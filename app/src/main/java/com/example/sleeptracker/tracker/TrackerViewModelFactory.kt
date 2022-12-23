package com.example.sleeptracker.tracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptracker.database.SleepDatabaseDao

class TrackerViewModelFactory(
    private val databaseDao: SleepDatabaseDao,
    private val application: Application,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrackerViewModel::class.java)) {
            return TrackerViewModel(databaseDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}