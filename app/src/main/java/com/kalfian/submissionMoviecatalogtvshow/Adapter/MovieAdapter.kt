package com.kalfian.submissionMoviecatalogtvshow.Adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kalfian.submissionMoviecatalogtvshow.Activity.DetailMovieActivity
import com.kalfian.submissionMoviecatalogtvshow.BuildConfig
import com.kalfian.submissionMoviecatalogtvshow.Model.ModelMovie
import com.kalfian.submissionMoviecatalogtvshow.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private var movie: ArrayList<ModelMovie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val API_URL = BuildConfig.MOVIE_PATH
        val data = movie[position]
        holder.view.movie_name.text = data.name
        holder.view.movie_description.text = data.overview.subSequence(0, 100)
        Picasso.get().load(API_URL + data.image).into(holder.view.movie_image)

        holder.movie = data
    }

    class MovieViewHolder(val view: View, var movie: ModelMovie? = null) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                val moveWithObjectIntent = Intent(view.context, DetailMovieActivity::class.java)
                moveWithObjectIntent.putExtra(DetailMovieActivity.DataDetail.EXTRA_PERSON, movie)
                view.context.startActivity(moveWithObjectIntent)
            }
        }
    }
}
