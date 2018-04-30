package com.varunbehl.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.varunbehl.myapplication.DataRepository
import com.varunbehl.myapplication.MyApplication
import com.varunbehl.myapplication.dataBase.DatabaseUtil
import com.varunbehl.myapplication.dataClass.MovieItem
import com.varunbehl.myapplication.dataClass.TvEntity
import com.varunbehl.myapplication.network.DataInterface
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject


class MainViewModel(application: Application) : AndroidViewModel(application) {


    lateinit var dataInterface: DataInterface
        @Inject set

    private var dataRepository: DataRepository


    var movieList: LiveData<PagedList<MovieItem>>
    var tvList: LiveData<PagedList<TvEntity.TvShowItem>>


    private var executor: Executor = Executors.newFixedThreadPool(5)


    init {
        (application as MyApplication).appComponent?.inject(this)
        dataRepository = DataRepository(dataInterface)

        val movieDataSourceFactory = MovieDataSourceFactory(executor, dataRepository)
        val tvShowDataSourceFactory = TvShowDataSourceFactory(executor, dataRepository)


        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20).build()


        movieList = LivePagedListBuilder(movieDataSourceFactory, pagedListConfig)
                .build()

        tvList = LivePagedListBuilder(tvShowDataSourceFactory, pagedListConfig)
                .build()

    }
    var myBookMarksList = DatabaseUtil.getInstance(getApplication()).userPreferenceDao().all


}