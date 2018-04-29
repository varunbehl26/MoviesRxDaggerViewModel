package com.varunbehl.myapplication

import com.varunbehl.myapplication.dataClass.Movie
import com.varunbehl.myapplication.dataClass.MovieDetail
import com.varunbehl.myapplication.dataClass.TvEntity
import com.varunbehl.myapplication.dataClass.TvShowDetail
import com.varunbehl.myapplication.network.DataInterface
import rx.Observable
import javax.inject.Singleton


@Singleton
class DataRepository(var dataInterface: DataInterface) {
    private val API_KEY = ""

    fun fetchPopularDataFromServer(page: Int): Observable<Movie>? {
        return dataInterface.listMoviesInfo("popular", page, API_KEY)
    }

    fun fetchMovieDetailFromServer(id: Int): Observable<MovieDetail>? {
        return dataInterface.getMovieDetail(id, API_KEY, "credits,videos,images,similar,recommendations")
    }

    fun fetchPopularTvShowsDataFromServer(page: Int): Observable<TvEntity>? {
        return dataInterface.listTvShows("popular", page, API_KEY)
    }

    fun fetchTvShowsDetailFromServer(id: Int): Observable<TvShowDetail>? {
        return dataInterface.getTvShowDetail(id, "credits,videos,images,similar,recommendations", API_KEY)
    }

}
