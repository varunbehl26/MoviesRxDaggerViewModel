package com.varunbehl.myapplication.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.varunbehl.myapplication.BR
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.dataClass.TvEntity
import com.varunbehl.myapplication.databinding.TvItemLayoutBinding


private val wallpaper_resolution = "w342"
private val placeholder_resolution = "w92"
private val low_resolution = "w185"
private val carousal_resolution = "w300"


class TvPagedListAdapter(private val onMovieSelected: (TvEntity.TvShowItem, View, Int) -> Unit) :
        PagedListAdapter<TvEntity.TvShowItem, TvPagedListAdapter.ViewHolder>(POST_COMPARATOR) {
    private var binding: TvItemLayoutBinding? = null

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<TvEntity.TvShowItem>() {
            override fun areContentsTheSame(oldItem: TvEntity.TvShowItem, newItem: TvEntity.TvShowItem): Boolean = oldItem == newItem
            override fun areItemsTheSame(oldItem: TvEntity.TvShowItem, newItem: TvEntity.TvShowItem): Boolean = oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.tv_item_layout, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvPagedListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position), onMovieSelected, position)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    class ViewHolder(private val binding: TvItemLayoutBinding?) : RecyclerView.ViewHolder(binding!!.root) {

        fun bind(tvShowItem: TvEntity.TvShowItem?, onMovieSelected: (TvEntity.TvShowItem, View, Int) -> Unit, position: Int) {
            ViewCompat.setTransitionName(binding?.image!!, tvShowItem?.original_name)
            binding.setVariable(BR.item, tvShowItem)
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                if (tvShowItem != null) {
                    onMovieSelected(tvShowItem, binding.root, position)
                }
            }
        }
    }
}

