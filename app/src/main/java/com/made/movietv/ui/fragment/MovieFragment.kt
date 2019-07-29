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
import com.made.movietv.adapter.MovieAdapter
import com.made.movietv.model.ModelMovie
import com.made.movietv.presenter.MoviePresenter
import com.made.movietv.ui.activity.DetailMovie
import com.made.movietv.view.MovieView
import dmax.dialog.SpotsDialog
import org.jetbrains.anko.support.v4.intentFor

class MovieFragment : Fragment(), MovieView {

    lateinit var recyclerView: RecyclerView
    lateinit var dialog: AlertDialog
    lateinit var mPresenter: MoviePresenter
    lateinit var mAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_movie, container, false)
        recyclerView = view.findViewById(R.id.movie_recyclerView)
        mPresenter = MoviePresenter(this, activity?.applicationContext!!)
        mPresenter.loadData()
        return view
    }

    override fun addData(arrayItem: ArrayList<ModelMovie>) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = MovieAdapter(arrayItem) {
            startActivity(intentFor<DetailMovie>("name" to it))
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
