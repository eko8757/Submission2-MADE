package com.made.movietv.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.made.movietv.R
import com.made.movietv.adapter.TvAdapter
import com.made.movietv.model.ModelTv
import com.made.movietv.presenter.TVPresenter
import com.made.movietv.ui.activity.DetailTvShow
import com.made.movietv.view.TVView
import dmax.dialog.SpotsDialog
import org.jetbrains.anko.support.v4.intentFor

class TvFragment : Fragment(), TVView {

    lateinit var recyclerView: RecyclerView
    lateinit var dialog: AlertDialog
    lateinit var mPresenter: TVPresenter
    lateinit var mAdapter: TvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_tv, container, false)
        recyclerView = view.findViewById(R.id.tv_recyclerView)
        mPresenter = TVPresenter(this, activity?.applicationContext!!)
        mPresenter.loadData()
        return view
    }

    override fun addData(arrayItem: ArrayList<ModelTv>) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = TvAdapter(arrayItem) {
            startActivity(intentFor<DetailTvShow>("name" to it))
        }
        recyclerView.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }

    override fun showDialog() {
        dialog = SpotsDialog.Builder().setTheme(R.style.Custom).setContext(context).build()
        dialog.show()
    }

    override fun hideDialog() {
        dialog.dismiss()
    }
}
