package com.varunbehl.myapplication.adapter

import androidx.fragment.app.FragmentManager
import com.varunbehl.myapplication.dataClass.MovieDetail
import com.varunbehl.myapplication.fragment.CastFragment
import com.varunbehl.myapplication.fragment.ImagesFragment
import com.varunbehl.myapplication.fragment.MovieDetailOverviewFragment


class MovieFragmentPagerAdapter(fm: FragmentManager, it: MovieDetail) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {
    private val tabTitles = arrayOf("Details", "Cast", "Images", "Cast", "Seasons")
    private val PAGE_COUNT = 3

    var movieDetail: MovieDetail = it


    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return when (position) {
            0 -> MovieDetailOverviewFragment.newInstance(movieDetail)
            1 -> CastFragment.newInstance(1, movieDetail.credits.cast, movieDetail.id)
            2 -> ImagesFragment.newInstance(2, movieDetail.images.posters, movieDetail.id)
            else -> CastFragment.newInstance(1, movieDetail.credits.cast, movieDetail.id)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }


}