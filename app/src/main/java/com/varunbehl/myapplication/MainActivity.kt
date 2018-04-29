package com.varunbehl.myapplication

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.varunbehl.myapplication.adapter.MovieListDataAdapter
import com.varunbehl.myapplication.entity.Picture.Picture_Detail
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val API_KEY = "29c90a4aee629499a2149041cc6a0ffd"


    var mGitHubApiInterface: GitHubApiInterface? = null
        @Inject set


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).getGitHubComponent().inject(this)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        fetchPopularDataFromServer()
    }


    fun fetchPopularDataFromServer() {
        val rand = Random()
        val pageToQuery = rand.nextInt(5) + 1
        var popularObservable = mGitHubApiInterface?.listMoviesInfo("popular", pageToQuery, API_KEY)

        popularObservable
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe(object : Subscriber<Picture_Detail>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        horizontalGridView.setVisibility(View.GONE)
                    }


                    override fun onNext(tv: Picture_Detail) {
                        val popularTvDataAdapter = MovieListDataAdapter(this@MainActivity, tv.getResults(), 1)
                        horizontalGridView.setAdapter(popularTvDataAdapter)
                        popularTvDataAdapter.notifyDataSetChanged()
                        horizontalGridView.setVisibility(View.VISIBLE)
                    }
                }
                )
    }
}
