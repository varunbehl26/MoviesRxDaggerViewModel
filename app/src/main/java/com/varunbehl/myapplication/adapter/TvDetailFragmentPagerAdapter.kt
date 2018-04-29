package com.varunbehl.myapplication.adapter

import android.content.Context
import com.varunbehl.myapplication.dataClass.TvShowDetail
import com.varunbehl.myapplication.fragment.CastFragment
import com.varunbehl.myapplication.fragment.ImagesFragment
import com.varunbehl.myapplication.fragment.TvDetailOverviewFragment


class TvDetailFragmentPagerAdapter(fm: androidx.fragment.app.FragmentManager, private val context: Context, it: TvShowDetail) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {
    private val tabTitles = arrayOf("Details", "Cast", "Images", "Cast", "Seasons")
    private val PAGE_COUNT = 3

    var tvShowDetail: TvShowDetail = it


    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return when (position) {
            0 -> TvDetailOverviewFragment.newInstance(tvShowDetail)
            1 -> CastFragment.newInstance(1, tvShowDetail.credits?.cast, tvShowDetail.id!!)
            2 -> ImagesFragment.newInstance(2, tvShowDetail.images?.posters, tvShowDetail.id!!)
            else -> CastFragment.newInstance(1, tvShowDetail.credits?.cast, tvShowDetail.id!!)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }


}