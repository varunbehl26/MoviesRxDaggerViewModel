package com.varunbehl.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.adapter.MyCastRecyclerViewAdapter
import com.varunbehl.myapplication.dataClass.CastItem
import kotlinx.android.synthetic.main.fragment_cast_list.view.*


class CastFragment : androidx.fragment.app.Fragment() {

    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var castItemList: ArrayList<CastItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            castItemList = it.getParcelableArrayList<CastItem>("casts")
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
            val filteredList = castItemList.toMutableList()
            for (cast: CastItem? in castItemList) {
                if (cast?.profilePath == null) {
                    filteredList.remove(cast)
                }
            }
            adapter = MyCastRecyclerViewAdapter(filteredList, listener)
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
        fun onListFragmentInteraction(item: CastItem?)
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"


        @JvmStatic
        fun newInstance(columnCount: Int, values: List<CastItem>?, id: Long) =
                CastFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                        putParcelableArrayList("casts", ArrayList(values))
                        putLong("id", id)
                    }
                }
    }
}
