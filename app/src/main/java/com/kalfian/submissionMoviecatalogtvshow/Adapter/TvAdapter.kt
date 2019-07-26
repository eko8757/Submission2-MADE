package com.kalfian.submissionMoviecatalogtvshow.Adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kalfian.submissionMoviecatalogtvshow.Activity.DetailTvActivity
import com.kalfian.submissionMoviecatalogtvshow.BuildConfig
import com.kalfian.submissionMoviecatalogtvshow.Model.ModelTv
import com.kalfian.submissionMoviecatalogtvshow.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tv.view.*

class TvAdapter(private var tv: ArrayList<ModelTv>) : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.item_tv, parent, false)
        return TvViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return tv.size
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val API_URL = BuildConfig.MOVIE_PATH
        val data = tv[position]
        holder.view.tv_name.text = data.name
        holder.view.tv_description.text = data.overview.subSequence(0, 100)
        Picasso.get().load(API_URL + data.image).into(holder.view.tv_image)

        holder.mTv = data
    }

    class TvViewHolder(val view: View, var mTv: ModelTv? = null) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                val moveWithObjectIntent = Intent(view.context, DetailTvActivity::class.java)
                moveWithObjectIntent.putExtra(DetailTvActivity.DataDetail.EXTRA_PERSON, mTv)
                view.context.startActivity(moveWithObjectIntent)
            }
        }
    }
}