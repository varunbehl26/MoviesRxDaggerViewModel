package com.varunbehl.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.adapter.TvPagedListAdapter
import com.varunbehl.myapplication.dataClass.TvEntity
import com.varunbehl.myapplication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.view.*
import kotlinx.android.synthetic.main.fragment_movie.*

class TvShowsFragment : Fragment() {

    private lateinit var clickListener: TvFragmentClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickListener = activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val model = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

            model.tvList.observe(this, Observer<PagedList<TvEntity.TvShowItem>> {
                recyclerView.layoutManager = GridLayoutManager(context, 2)
                val movieAdapter = TvPagedListAdapter { tvShowItem: TvEntity.TvShowItem, view: View, pos: Int ->
                    clickListener.onTvShowItemClick(tvShowItem.id!!, view.image)
                }
                recyclerView.adapter = movieAdapter
                movieAdapter.submitList(it)
            })

        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                TvShowsFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    interface TvFragmentClickListener {
        fun onTvShowItemClick(movieId: Int, sharedImageView: ImageView)

    }
}
