package com.varunbehl.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.varunbehl.myapplication.DataRepository
import com.varunbehl.myapplication.MyApplication
import com.varunbehl.myapplication.dataBase.DatabaseUtil
import com.varunbehl.myapplication.dataBase.UserPreference
import com.varunbehl.myapplication.dataClass.MovieDetail
import com.varunbehl.myapplication.network.DataInterface
import rx.Observable
import javax.inject.Inject


class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var dataInterface: DataInterface
        @Inject set

    private var dataRepository: DataRepository


    init {
        (application as MyApplication).appComponent?.inject(this)
        dataRepository = DataRepository(dataInterface)
    }

    fun getMovieDetails(id: Int): Observable<MovieDetail>? {
        return dataRepository.fetchMovieDetailFromServer(id)
    }

    fun saveMovieAsBookmark(movieItem: MovieDetail): Unit {
        val userPreference: UserPreference = UserPreference()
        userPreference.movieId = movieItem.id
        userPreference.backdropPath = movieItem.backdropPath
        userPreference.posterPath = movieItem.posterPath
        userPreference.title = movieItem.title
        DatabaseUtil.getInstance(getApplication()).userPreferenceDao().insert(userPreference)
    }

}

