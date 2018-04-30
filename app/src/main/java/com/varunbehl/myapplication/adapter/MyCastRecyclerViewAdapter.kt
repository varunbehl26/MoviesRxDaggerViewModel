package com.varunbehl.myapplication.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.varunbehl.myapplication.BR
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.dataClass.CastItem
import com.varunbehl.myapplication.databinding.FragmentItemCastBinding
import com.varunbehl.myapplication.fragment.CastFragment.OnListFragmentInteractionListener


class MyCastRecyclerViewAdapter(
        private val mValues: List<CastItem>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyCastRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var binding: FragmentItemCastBinding? = null

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as CastItem
            mListener?.onListFragmentInteraction(item)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item_cast, parent, false)
        binding = DataBindingUtil.bind(view)
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mValues.size


    inner class ViewHolder(private val binding: FragmentItemCastBinding?) : RecyclerView.ViewHolder(binding!!.root) {

        fun bind(pos: Int) {
            //Null check mandatory else crash
            if (mValues[pos].profilePath != null) {
                binding?.setVariable(BR.castItem, mValues[pos])
                binding?.executePendingBindings()
            } else {
                binding?.root?.visibility = View.GONE
            }
        }
    }
}
