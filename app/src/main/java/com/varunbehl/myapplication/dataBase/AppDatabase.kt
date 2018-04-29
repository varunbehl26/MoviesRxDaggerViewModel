package com.varunbehl.myapplication.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(UserPreference::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userPreferenceDao(): UserPreferenceDao
}