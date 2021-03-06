package com.varunbehl.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.adapter.VideosRecyclerViewAdapter
import com.varunbehl.myapplication.dataClass.TvShowDetail
import com.varunbehl.myapplication.databinding.FragmentOverviewBinding


class TvDetailOverviewFragment : Fragment() {
    private val detail = "param1"
    private lateinit var binding: FragmentOverviewBinding

    private var param1: TvShowDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(detail)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)
        binding.txOverview.text = param1?.overview
//        binding.executePendingBindings()

        binding.listVideos.layoutManager = GridLayoutManager(context, 2, 1, false)
        binding.listVideos.adapter = VideosRecyclerViewAdapter(param1?.videos?.results!!, null)

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: TvShowDetail) =
                TvDetailOverviewFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(detail, param1)
                    }
                }
    }
}
