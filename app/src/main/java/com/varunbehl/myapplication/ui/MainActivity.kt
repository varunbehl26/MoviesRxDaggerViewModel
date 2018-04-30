package com.varunbehl.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.varunbehl.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MovieFragment.MovieFragmentClickListener, TvShowsFragment.TvFragmentClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_home
    }


    override fun onTvShowItemClick(tvId: Int, sharedImageView: ImageView) {
        val intent = Intent(this, TvShowDetailActivity::class.java)
        intent.putExtra(MainActivity.EXTRA_MOVIE_ITEM, tvId)
        intent.putExtra(MainActivity.EXTRA_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView))

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView)!!)

        ContextCompat.startActivity(this, intent, options.toBundle())

    }

    override fun onMovieItemClick(movieItemId: Int, sharedImageView: ImageView) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MainActivity.EXTRA_MOVIE_ITEM, movieItemId)
        intent.putExtra(MainActivity.EXTRA_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView))

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView)!!)

        ContextCompat.startActivity(this, intent, options.toBundle())
    }

    companion object {
        val EXTRA_MOVIE_ITEM: String = "EXTRA_MOVIE_ITEM"
        val EXTRA_IMAGE_TRANSITION_NAME: String? = "EXTRA_IMAGE_TRANSITION_NAME"
    }


    private val mOnNavigationItemSelectedListener = com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.parent_frameLayout, MovieFragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.parent_frameLayout, BookmarksFragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.parent_frameLayout, TvShowsFragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


}