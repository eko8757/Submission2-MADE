package com.kalfian.submissionMoviecatalogtvshow


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.kalfian.submissionMoviecatalogtvshow.Adapter.MovieAdapter
import com.kalfian.submissionMoviecatalogtvshow.Model.ModelMovie
import dmax.dialog.SpotsDialog

import kotlinx.android.synthetic.main.fragment_movie.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var arrayItem: ArrayList<ModelMovie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(this.activity?.applicationContext)
        movie_recyclerView.layoutManager = linearLayoutManager

        loadData()
    }

    private fun loadData() {
        val dialog: AlertDialog = SpotsDialog.Builder().setTheme(R.style.Custom).setContext(context).build()
        dialog.show()
        val queue = Volley.newRequestQueue(activity?.applicationContext)

        val url =
            BuildConfig.MOVIE_URL + "/top_rated?api_key=" + BuildConfig.MOVIE_API_KEY + "&language=en-US" + "&page=1"
        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Listener<String> { response ->
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

                        arrayItem.add(
                            ModelMovie(
                                name,
                                image,
                                overview,
                                release,
                                popularity,
                                vote
                            )
                        )
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                movie_recyclerView.adapter = MovieAdapter(arrayItem)
                dialog.dismiss()
            },
            Response.ErrorListener {
                print("Response: That didn't work!")
            })
        queue.add(stringReq)
    }

}
