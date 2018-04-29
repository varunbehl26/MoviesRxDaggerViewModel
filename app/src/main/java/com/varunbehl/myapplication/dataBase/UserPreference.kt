package com.varunbehl.myapplication.dataBase

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class UserPreference {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0
    @ColumnInfo(name = "movieId")
    var movieId: Long? = null
    @ColumnInfo(name = "title")
    var title: String? = ""
    @ColumnInfo(name = "poster_path")
    var posterPath: String? = ""
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = ""
}