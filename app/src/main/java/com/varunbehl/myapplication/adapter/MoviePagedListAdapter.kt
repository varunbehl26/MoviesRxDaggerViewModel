package com.varunbehl.myapplication.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.varunbehl.myapplication.BR
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.dataClass.MovieItem
import com.varunbehl.myapplication.databinding.MovieItemLayoutBinding


private val wallpaper_resolution = "w342"
private val placeholder_resolution = "w92"
private val low_resolution = "w185"
private val carousal_resolution = "w300"


class MoviePagedListAdapter(private val onMovieSelected: (MovieItem, View, Int) -> Unit) :
        PagedListAdapter<MovieItem, MoviePagedListAdapter.ViewHolder>(POST_COMPARATOR) {

    private var binding: ViewDataBinding? = null

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean = oldItem == newItem
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean = oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.movie_item_layout, parent, false)
        return ViewHolder(binding as MovieItemLayoutBinding)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onMovieSelected, position)
    }

    class ViewHolder(private val binding: MovieItemLayoutBinding?) : RecyclerView.ViewHolder(binding!!.root) {
        fun bind(movieItem: MovieItem?, onMovieSelected: (MovieItem, View, Int) -> Unit, position: Int) {
            ViewCompat.setTransitionName(binding?.image!!, movieItem?.originalTitle)

            binding.setVariable(BR.item, movieItem)
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                if (movieItem != null) {
                    onMovieSelected(movieItem, binding.root, position)
                }
            }
        }
    }
}


@BindingAdapter("bindimageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageView.scaleType = ImageView.ScaleType.FIT_CENTER
    if (imageUrl != "") {
        Glide.with(imageView.context)
                .load(IMAGE_POSTER_BASE_URL + imageUrl)
                .into(imageView)
    }
}
