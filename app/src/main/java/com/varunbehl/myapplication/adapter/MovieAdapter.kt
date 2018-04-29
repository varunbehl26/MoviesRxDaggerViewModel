package com.varunbehl.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.varunbehl.myapplication.BR
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.dataBase.UserPreference
import com.varunbehl.myapplication.databinding.BookmarkMovieItemLayoutBinding


private val wallpaper_resolution = "w342"
private val placeholder_resolution = "w92"
private val low_resolution = "w185"
private val carousal_resolution = "w300"
var IMAGE_POSTER_BASE_URL = "https://image.tmdb.org/t/p/$wallpaper_resolution"


class MovieAdapter(private var itemList: List<UserPreference>, private val onMovieSelected: (UserPreference, View, Int) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var binding: BookmarkMovieItemLayoutBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.bookmark_movie_item_layout, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position], onMovieSelected, position)
    }

    class ViewHolder(private val binding: BookmarkMovieItemLayoutBinding?) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding!!.root) {
        fun bind(movieItem: UserPreference, onMovieSelected: (UserPreference, View, Int) -> Unit, position: Int) {
            ViewCompat.setTransitionName(binding?.image!!, movieItem.title)

            binding.setVariable(BR.bookmarkItem, movieItem)
            binding.executePendingBindings()
            binding.root.setOnClickListener({
                onMovieSelected(movieItem, binding.root, position)
            })
        }
    }
}
