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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.adapter.MoviePagedListAdapter
import com.varunbehl.myapplication.dataClass.MovieItem
import com.varunbehl.myapplication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.view.*
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment() {

    private lateinit var clickListener: MovieFragmentClickListener

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

            model.movieList.observe(this, Observer<PagedList<MovieItem>> {
                recyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
                val movieAdapter = MoviePagedListAdapter { movieItem: MovieItem, view: View, pos: Int ->
                    clickListener.onMovieItemClick(movieItem.id!!, view.image)
                }
                recyclerView.adapter = movieAdapter
                movieAdapter.submitList(it)
            })

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                MovieFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    interface MovieFragmentClickListener {
        fun onMovieItemClick(movieId: Int, sharedImageView: ImageView)

    }
}
