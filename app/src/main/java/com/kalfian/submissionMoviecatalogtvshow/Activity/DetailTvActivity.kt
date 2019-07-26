package com.kalfian.submissionMoviecatalogtvshow.Activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.kalfian.submissionMoviecatalogtvshow.BuildConfig
import com.kalfian.submissionMoviecatalogtvshow.Model.ModelTv
import com.kalfian.submissionMoviecatalogtvshow.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tv.*

class DetailTvActivity : AppCompatActivity() {
    object DataDetail {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mTv = intent.getParcelableExtra(DataDetail.EXTRA_PERSON) as ModelTv
        title = mTv.name

        tv_movie_name.text = mTv.name
        tv_vote.text = mTv.vote.toString()
        tv_release.text = mTv.firstAirDate
        tv_popularity.text = mTv.popularity.toString()
        tv_overview.text = mTv.overview

        val API_URL = BuildConfig.MOVIE_PATH
        Picasso.get().load(API_URL + mTv.image).into(thumbnail)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
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
