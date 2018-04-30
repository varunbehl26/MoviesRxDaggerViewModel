package com.varunbehl.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.varunbehl.myapplication.DataRepository
import com.varunbehl.myapplication.MyApplication
import com.varunbehl.myapplication.dataBase.DatabaseUtil
import com.varunbehl.myapplication.dataBase.UserPreference
import com.varunbehl.myapplication.dataClass.TvShowDetail
import com.varunbehl.myapplication.network.DataInterface
import rx.Observable
import javax.inject.Inject


class TvShowDetailViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var dataInterface: DataInterface
        @Inject set

    private var dataRepository: DataRepository


    init {
        (application as MyApplication).appComponent?.inject(this)
        dataRepository = DataRepository(dataInterface)
    }

    fun getMovieDetails(id: Int): Observable<TvShowDetail>? {
        return dataRepository.fetchTvShowsDetailFromServer(id)
    }

    fun saveTvShowAsBookmark(movieItem: TvShowDetail): Unit {
        val userPreference: UserPreference = UserPreference()
        userPreference.movieId = movieItem.id
        userPreference.backdropPath = movieItem.backdrop_path
        userPreference.posterPath = movieItem.poster_path
        userPreference.title = movieItem.original_name
        DatabaseUtil.getInstance(getApplication()).userPreferenceDao().insert(userPreference)
    }

}

