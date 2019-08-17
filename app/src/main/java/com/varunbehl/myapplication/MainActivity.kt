package com.varunbehl.myapplication

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.varunbehl.myapplication.entity.Picture.Picture_Detail
import com.varunbehl.myapplication.network.DataInterface
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val API_KEY = ""
    var dataInterface: DataInterface? = null
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
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        (application as MyApplication).appComponent.inject(this)
        fetchPopularDataFromServer()

    }


    fun fetchPopularDataFromServer() {
        val rand = Random()
        val pageToQuery = rand.nextInt(5) + 1
        var popularObservable = dataInterface?.listMoviesInfo("popular", pageToQuery, API_KEY)

        popularObservable
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe(object : Subscriber<Picture_Detail>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }


                    override fun onNext(tv: Picture_Detail) {
                        Log.v("---",tv.toString())

                    }
                }
                )

    }
}
