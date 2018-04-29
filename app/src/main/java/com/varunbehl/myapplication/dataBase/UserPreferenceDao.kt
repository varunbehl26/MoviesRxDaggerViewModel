package com.varunbehl.myapplication.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserPreferenceDao {
    @get:Query("SELECT * FROM UserPreference")
    val all: LiveData<List<UserPreference>>

    @Query("SELECT * FROM UserPreference WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<UserPreference>

    @Insert
    fun insert(user: UserPreference)

    @Delete
    fun delete(user: UserPreference)
}