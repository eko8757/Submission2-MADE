package com.kalfian.submissionMoviecatalogtvshow.Activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.kalfian.submissionMoviecatalogtvshow.BuildConfig
import com.kalfian.submissionMoviecatalogtvshow.Model.ModelMovie
import com.kalfian.submissionMoviecatalogtvshow.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {
    object DataDetail {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra(DataDetail.EXTRA_PERSON) as ModelMovie
        title = movie.name

        tv_movie_name.text = movie.name
        tv_vote.text = movie.vote.toString()
        tv_release.text = movie.release
        tv_popularity.text = movie.popularity.toString()
        tv_overview.text = movie.overview

        val API_URL = BuildConfig.MOVIE_PATH
        Picasso.get().load(API_URL + movie.image).into(thumbnail)

        Log.d("DATA_MOVIE", movie.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId ?: 0 == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
