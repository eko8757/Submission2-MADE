package com.made.movietv.presenter

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.made.movietv.BuildConfig
import com.made.movietv.model.ModelMovie
import com.made.movietv.view.MovieView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MoviePresenter(val view: MovieView, val context: Context) {

    private var arrayItem: ArrayList<ModelMovie> = ArrayList()

    fun loadData() {
        view.showDialog()
        val queue = Volley.newRequestQueue(context)
        val url =
            BuildConfig.MOVIE_URL + "/top_rated?api_key=" + BuildConfig.MOVIE_API_KEY + "&language=en-US" + "&page=1"

        val stringReq = StringRequest(
            Request.Method.GET, url, Response.Listener<String> { response ->
                val strResp = response.toString()
                try {
                    val jsonObj = JSONObject(strResp)
                    val jsonArray: JSONArray = jsonObj.getJSONArray("results")
                    for (i in 0 until jsonArray.length()) {
                        val item = jsonArray.getJSONObject(i)
                        val name: String = item.get("title").toString()
                        val image: String = item.get("poster_path").toString()
                        val overview: String = item.get("overview").toString()
                        val release: String = item.get("release_date").toString()
                        val popularity: Int = item.getInt("popularity")
                        val vote: Int = item.getInt("vote_average")
                        arrayItem.add(ModelMovie(name, image, overview, release, popularity, vote))
                        view.addData(arrayItem)
                        view.hideDialog()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener {
                print("Response: That didn't work!")
            })
        queue.add(stringReq)
    }
}