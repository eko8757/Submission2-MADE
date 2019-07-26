package com.kalfian.submissionMoviecatalogtvshow


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.kalfian.submissionMoviecatalogtvshow.Adapter.TvAdapter
import com.kalfian.submissionMoviecatalogtvshow.Model.ModelTv
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class TvShowFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var arrayItem: ArrayList<ModelTv> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(this.activity?.applicationContext)
        tv_recyclerView.layoutManager = linearLayoutManager
        loadData()
    }

    private fun loadData() {
        val dialog: AlertDialog = SpotsDialog.Builder().setTheme(R.style.Custom).setContext(context).build()
        dialog.show()

        val queue = Volley.newRequestQueue(activity?.applicationContext)

        val url = BuildConfig.TV_URL + "/top_rated?api_key=" + BuildConfig.MOVIE_API_KEY + "&language=en-US" + "&page=1"
        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Listener<String> { response ->
                val strResp = response.toString()
                Log.d("data", strResp)
                try {
                    val jsonObj = JSONObject(strResp)
                    val jsonArray: JSONArray = jsonObj.getJSONArray("results")
                    for (i in 0 until jsonArray.length()) {
                        val item = jsonArray.getJSONObject(i)
                        val name: String = item.get("name").toString()
                        val image: String = item.get("poster_path").toString()
                        val overview: String = item.get("overview").toString()
                        val firstAir: String = item.get("first_air_date").toString()
                        val popularity: Int = item.getInt("popularity")
                        val vote: Int = item.getInt("vote_average")

                        arrayItem.add(
                            ModelTv(
                                name,
                                image,
                                overview,
                                firstAir,
                                popularity,
                                vote
                            )
                        )
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                tv_recyclerView.adapter = TvAdapter(arrayItem)
                dialog.dismiss()
            },
            Response.ErrorListener {
                print("Response: That didn't work!")
            })
        queue.add(stringReq)
    }

}
