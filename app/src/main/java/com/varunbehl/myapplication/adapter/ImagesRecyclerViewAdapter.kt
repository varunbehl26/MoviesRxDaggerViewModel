package com.varunbehl.myapplication.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.varunbehl.myapplication.BR
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.dataClass.PostersItem
import com.varunbehl.myapplication.databinding.FragmentItemImageBinding
import com.varunbehl.myapplication.fragment.ImagesFragment


class ImagesRecyclerViewAdapter(
        private val mValues: ArrayList<PostersItem>,
        private val mListener: ImagesFragment.OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<ImagesRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var binding: FragmentItemImageBinding? = null

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as PostersItem
            mListener?.onListFragmentInteraction(item)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item_image, parent, false)
        binding = DataBindingUtil.bind(view)
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mValues.size


    inner class ViewHolder(private val binding: FragmentItemImageBinding?) : RecyclerView.ViewHolder(binding!!.root) {

        fun bind(pos: Int) {
            //Null check mandatory else crash
            if (mValues[pos].filePath != null) {
                binding?.setVariable(BR.posterItem, mValues[pos])
                binding?.executePendingBindings()
            } else {
                binding?.root?.visibility = View.GONE
            }
        }
    }


}
//
//@BindingAdapter("bindimageUrl")
//fun loadImage(imageView: ImageView, imageUrl: String?) {
//    imageView.scaleType = ImageView.ScaleType.FIT_CENTER
//    if (imageUrl != null && imageUrl != "") {
//        Glide.with(imageView.context)
//                .load(IMAGE_POSTER_BASE_URL + imageUrl)
//                .into(imageView)
//    }
//}
