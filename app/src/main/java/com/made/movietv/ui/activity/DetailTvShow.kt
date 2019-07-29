package com.made.movietv.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.made.movietv.BuildConfig
import com.made.movietv.model.ModelTv
import com.made.movietv.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tv.*

class DetailTvShow : AppCompatActivity() {

    var items: ModelTv? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val i = intent
        items = i.getParcelableExtra("name")

        tv_movie_name.text = items?.name
        tv_vote.text = items?.vote.toString()
        tv_release.text = items?.firstAirDate
        tv_popularity.text = items?.popularity.toString()
        tv_overview.text = items?.overview

        val API_URL = BuildConfig.MOVIE_PATH
        Picasso.get().load(API_URL + items?.image).into(thumbnail)
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
        if (item?.itemId ?: 0 == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
