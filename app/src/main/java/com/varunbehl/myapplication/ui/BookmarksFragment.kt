package com.varunbehl.myapplication.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.adapter.MovieAdapter
import com.varunbehl.myapplication.dataBase.UserPreference
import com.varunbehl.myapplication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.view.*
import kotlinx.android.synthetic.main.fragment_movie.*

class BookmarksFragment : Fragment() {

    private lateinit var clickListener: MovieFragment.MovieFragmentClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickListener = activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val model = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

           model.myBookMarksList.observe(this, Observer {
               recyclerView.layoutManager = GridLayoutManager(context, 2)
               val movieAdapter = MovieAdapter(it!!) { movieItem: UserPreference, view: View, _: Int ->
                   clickListener.onMovieItemClick(movieItem.id, view.image)
               }
               recyclerView.adapter = movieAdapter
           })

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                BookmarksFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}

