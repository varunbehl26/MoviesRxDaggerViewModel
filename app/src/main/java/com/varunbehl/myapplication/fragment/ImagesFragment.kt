package com.varunbehl.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.adapter.ImagesRecyclerViewAdapter
import com.varunbehl.myapplication.dataClass.PostersItem
import kotlinx.android.synthetic.main.fragment_cast_list.view.*


class ImagesFragment : androidx.fragment.app.Fragment() {

    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var postersItemList: ArrayList<PostersItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            postersItemList = it.getParcelableArrayList<PostersItem>("casts")
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cast_list, container, false)

        // Set the adapter
        with(view.list) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = ImagesRecyclerViewAdapter(postersItemList, listener)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: PostersItem?)
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"


        @JvmStatic
        fun newInstance(columnCount: Int, values: List<PostersItem>?, id: Long) =
                ImagesFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                        putParcelableArrayList("casts", ArrayList(values))
                        putLong("id", id)
                    }
                }
    }
}
