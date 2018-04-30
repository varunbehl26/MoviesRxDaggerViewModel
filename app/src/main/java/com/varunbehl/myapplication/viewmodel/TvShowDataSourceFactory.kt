package com.varunbehl.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.varunbehl.myapplication.DataRepository
import com.varunbehl.myapplication.dataClass.TvEntity
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.Executor

class TvShowDataSourceFactory(executor: Executor, var dataRepository: DataRepository) : DataSource.Factory<Long, TvEntity.TvShowItem>() {

    private val sourceLiveData = MutableLiveData<TvShowDataSource>()

    override fun create(): DataSource<Long, TvEntity.TvShowItem> {
        val source = TvShowDataSource(dataRepository)
        sourceLiveData.postValue(source)
        return source
    }
}

class TvShowDataSource(var dataRepository: DataRepository) : PageKeyedDataSource<Long, TvEntity.TvShowItem>() {

    var currentPage = 1

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, TvEntity.TvShowItem>) {

        dataRepository.fetchPopularTvShowsDataFromServer(currentPage)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({
                    val nextPage = it.page?.plus(1)
                    callback.onResult(it.results!! as MutableList<TvEntity.TvShowItem>, 0, nextPage?.toLong())
                }, {
                    Log.v("---", it.printStackTrace().toString())

                })


    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, TvEntity.TvShowItem>) {

        dataRepository.fetchPopularTvShowsDataFromServer(++currentPage)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({
                    val nextPage = it.page?.plus(1)
                    callback.onResult(it.results!! as MutableList<TvEntity.TvShowItem>, nextPage?.toLong())
                }, {
                    Log.v("---", it.printStackTrace().toString())
                })


    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, TvEntity.TvShowItem>) {

    }

}

