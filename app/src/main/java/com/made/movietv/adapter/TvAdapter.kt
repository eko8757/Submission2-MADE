package com.made.movietv.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.made.movietv.BuildConfig
import com.made.movietv.model.ModelTv
import com.made.movietv.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tv.view.*

class TvAdapter(private var tv: ArrayList<ModelTv>, val listener: (ModelTv) -> Unit)
    : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        return TvViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tv, parent, false))
    }

    override fun getItemCount(): Int = tv.size

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bindData(tv[position],listener)
    }

    class TvViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(item: ModelTv, listener: (ModelTv) -> Unit) {
            val API_URL = BuildConfig.MOVIE_PATH
            itemView.tv_name.text = item.name
            itemView.tv_description.text = item.overview
            Picasso.get().load(API_URL + item.image).into(itemView.tv_image)
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}