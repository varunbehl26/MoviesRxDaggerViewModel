package com.varunbehl.myapplication.adapter


import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.varunbehl.myapplication.BR
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.dataClass.VideoItem
import com.varunbehl.myapplication.databinding.FragmentItemVideoBinding
import com.varunbehl.myapplication.fragment.CastFragment.OnListFragmentInteractionListener


class VideosRecyclerViewAdapter(
        private val mValues: List<VideoItem>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<VideosRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var binding: FragmentItemVideoBinding? = null

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as VideoItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item_video, parent, false)
        binding = DataBindingUtil.bind(view)
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mValues.size


    inner class ViewHolder(private val binding: FragmentItemVideoBinding?) : RecyclerView.ViewHolder(binding!!.root) {

        fun bind(pos: Int) {
            //Null check mandatory else crash
            if (mValues[pos].key != null) {
                binding?.setVariable(BR.videoItem, mValues[pos])
                binding?.executePendingBindings()
                binding?.root!!.setOnClickListener { ContextCompat.startActivity(binding.root.context, Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + mValues[pos].key)), null) }
            } else {
                binding?.root?.visibility = View.GONE
            }
        }
    }


}

@BindingAdapter("bindVideoimageUrl")
fun loadVideoImage(imageView: ImageView, imageUrl: String?) {
    imageView.scaleType = ImageView.ScaleType.FIT_CENTER
    if (imageUrl != null && imageUrl != "") {
        Glide.with(imageView.context)
                .load("https://img.youtube.com/vi/$imageUrl/0.jpg")
                .into(imageView)
    }
}