package com.made.movietv.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.made.movietv.BuildConfig
import com.made.movietv.model.ModelMovie
import com.made.movietv.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private var movie: ArrayList<ModelMovie>, val listener: (ModelMovie) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = movie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(movie[position], listener)
    }

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(item: ModelMovie, listener: (ModelMovie) -> Unit) {
            val API_URL = BuildConfig.MOVIE_PATH
            itemView.movie_name.text = item.name
            itemView.movie_description.text = item.overview
            Picasso.get().load(API_URL + item.image).into(itemView.movie_image)
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}
