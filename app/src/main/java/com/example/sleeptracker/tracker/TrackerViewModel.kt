package com.example.sleeptracker.tracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.sleeptracker.database.SleepDatabaseDao

class TrackerViewModel(
    val database: SleepDatabaseDao,
     application: Application,
) : AndroidViewModel(application) {
}