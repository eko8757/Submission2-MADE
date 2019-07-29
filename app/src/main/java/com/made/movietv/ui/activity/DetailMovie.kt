package com.made.movietv.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.made.movietv.BuildConfig
import com.made.movietv.model.ModelMovie
import com.made.movietv.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovie : AppCompatActivity() {

    var items: ModelMovie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val i = intent
        items = i.getParcelableExtra("name")

        tv_movie_name.text = items?.name
        tv_vote.text = items?.vote.toString()
        tv_release.text = items?.release
        tv_popularity.text = items?.popularity.toString()
        tv_overview.text = items?.overview

        val API_URL = BuildConfig.MOVIE_PATH
        Picasso.get().load(API_URL + items?.image).into(thumbnail)

        Log.d("DATA_MOVIE", items?.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId ?: 0 == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
