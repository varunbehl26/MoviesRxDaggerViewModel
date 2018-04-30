package com.varunbehl.myapplication.dataBase

import android.content.Context
import androidx.room.Room

object DatabaseUtil {

    private var appDatabase: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase::class.java, "MovieDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }
        return appDatabase as AppDatabase
    }

}
